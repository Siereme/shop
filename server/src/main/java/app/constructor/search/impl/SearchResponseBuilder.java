package app.constructor.search.impl;

import app.constructor.search.ISearchResponseBuilder;
import app.model.dto.search.ISearchResponse;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import app.model.dto.search.RangePriceDTO;
import app.model.product.Product;
import app.model.product.option.OptionValue;
import app.model.product.option.Option;
import app.repository.product.ProductOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchResponseBuilder<T extends ISearchResponse> implements ISearchResponseBuilder<T> {

    @Autowired
    private ProductOptionRepository optionRepo;
    private T response;
    private final List<OptionValueDTO> checkedValues = new ArrayList<>();
    private RangePriceDTO fullRangePrice;

    @Override
    public SearchResponseBuilder<T> create(T response){
        this.response = response;
        checkedValues.clear();
        fullRangePrice = new RangePriceDTO();
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setProducts(List<Product> products){
        response.setProducts(products);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setFullRangePrices(List<Double> prices){
        fullRangePrice = prices.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            int priceMin = list.stream().min(Double::compareTo).orElse(0d).intValue();
                            int priceMax = list.stream().max(Double::compareTo).orElse(0d).intValue();
                            return new RangePriceDTO(priceMin, priceMax, priceMin, priceMax);
                        }
                ));
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setRangePrice(RangePriceDTO rangePriceDTO){
        RangePriceDTO rangePrice;
        if(rangePriceDTO == null){
            rangePrice = response.getProducts().stream()
                    .collect(Collectors.collectingAndThen(
                            Collectors.toList(),
                            list -> {
                                int minPrice = list.stream()
                                        .map(product -> product.getPrice().intValue())
                                        .min(Integer::compareTo).orElse(0);
                                int maxPrice = list.stream()
                                        .map(product -> product.getPrice().intValue())
                                        .max(Integer::compareTo).orElse(0);
                                return new RangePriceDTO(minPrice, maxPrice, minPrice, maxPrice);
                            }
                    ));
        } else {
            rangePrice = new RangePriceDTO(
                    Math.max(rangePriceDTO.getRangeMin(), fullRangePrice.getRangeMin()),
                    Math.min(rangePriceDTO.getRangeMax(), fullRangePrice.getRangeMax()),
                    Math.max(rangePriceDTO.getPriceMin(), fullRangePrice.getPriceMin()),
                    Math.min(rangePriceDTO.getPriceMax(), fullRangePrice.getPriceMax())
            );
        }
        response.setRangePrice(rangePrice);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setCheckedOptions(List<OptionDTO> options){
        List<OptionValueDTO> values = options.stream()
                .map(OptionDTO::getValues)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        checkedValues.addAll(values);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setOptions(){
        //Get options for products
        List<OptionValue> productOptions = response.getProducts().stream()
                .map(Product::getOptions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        ////Group the type and values of options from a selection of products
        Map<Long, List<String>> optionValues = productOptions.stream()
                .collect(Collectors.groupingBy(
                        option -> option.getOption().getId(),
                        Collectors.mapping(OptionValue::getValue, Collectors.toList())));

        ////Get type ids
        List<Long> typesIds = productOptions.stream()
                .map(option -> option.getOption().getId())
                .distinct().collect(Collectors.toList());

        ////Get types of options by ids
        List<Option> optionTypes = optionRepo.findAllOptionByIds(typesIds).orElse(new ArrayList<>());

        ////Construct list of option dto
        List<OptionDTO> options = new ArrayList<>();
        for(Option optionType : optionTypes){
            OptionDTO option = new OptionDTO();

            option.setId(optionType.getId());
            option.setType(optionType.getType());

            List<OptionValueDTO> optionValue = optionType.getValues().stream()
                    .filter(item -> optionValues.get(option.getId()).contains(item.getValue()))
                    .map(item -> new OptionValueDTO(
                            item.getValue(),
                            checkedValues.stream().anyMatch(o -> Objects.equals(o.getValue(), item.getValue()))
                    )).collect(Collectors.toList());
            option.setValues(optionValue);

            if(!option.getValues().isEmpty()){
                options.add(option);
            }
        }

        response.setOptions(options);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setPage(long page, long pagesCount){
        response.setPage(page);
        response.setPagesCount(pagesCount);
        return this;
    }

    @Override
    public T build(){
        return response;
    }

}
