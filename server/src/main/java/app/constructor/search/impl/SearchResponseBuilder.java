package app.constructor.search.impl;

import app.constructor.search.ISearchResponseBuilder;
import app.model.dto.search.ISearchResponse;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import app.model.dto.search.RangePriceDTO;
import app.model.product.Product;
import app.model.product.option.Option;
import app.model.product.option.OptionValue;
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
    private final List<OptionValueDTO> checkedOptionValues = new ArrayList<>();
    private RangePriceDTO fullRangePrice;

    @Override
    public SearchResponseBuilder<T> create(T response) {
        this.response = response;
        checkedOptionValues.clear();
        fullRangePrice = new RangePriceDTO();
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setProducts(List<Product> products) {
        response.setProducts(products);
        return this;
    }

    private RangePriceDTO getRangePrice(List<Double> prices){
        return prices.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            int priceMin = list.stream().min(Double::compareTo).orElse(0d).intValue();
                            int priceMax = list.stream().max(Double::compareTo).orElse(0d).intValue();
                            return new RangePriceDTO(priceMin, priceMax, priceMin, priceMax);
                        }
                ));
    }

    @Override
    public SearchResponseBuilder<T> setFullRangePrices(List<Double> prices) {
        fullRangePrice = getRangePrice(prices);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setRangePrice(RangePriceDTO rangePriceDTO) {
        RangePriceDTO rangePrice;
        if(rangePriceDTO == null || !rangePriceDTO.isValid()){
            List<Double> prices = response.getProducts().stream()
                    .map(Product::getPrice)
                    .collect(Collectors.toList());
            rangePrice = getRangePrice(prices);
        }
        else {
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
    public SearchResponseBuilder<T> setCheckedOptions(List<OptionDTO> options) {
        List<OptionValueDTO> values = options.stream()
                .map(OptionDTO::getValues)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        checkedOptionValues.addAll(values);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setOptions(List<List<OptionValue>> optionsValues) {
        //Get list of non-null options
        List<OptionValue> optionsList = optionsValues.stream()
                .flatMap(Collection::stream)
                .filter(option -> Objects.nonNull(option.getOption()))
                .collect(Collectors.toList());

        //Group the type and values of options from a selection of products
        Map<Long, List<String>> optionsGroups = optionsList.stream()
                .collect(Collectors.groupingBy(
                        option -> option.getOption().getId(),
                        Collectors.mapping(OptionValue::getValue, Collectors.toList())
                ));

        //Get types of options by ids
        List<Option> optionsTypes = optionRepo.findAllOptionByIds(optionsGroups.keySet()).orElse(new ArrayList<>());

        //Construct list of options dto
        List<OptionDTO> options = new ArrayList<>();
        for (Option optionType : optionsTypes) {
            List<OptionValueDTO> values = optionType.getValues().stream()
                    .filter(item -> optionsGroups.get(optionType.getId()).contains(item.getValue()))
                    .map(item -> new OptionValueDTO(
                            item.getValue(),
                            checkedOptionValues.stream().anyMatch(o -> Objects.equals(o.getValue(), item.getValue()))
                    )).collect(Collectors.toList());

            if (!values.isEmpty()) {
                OptionDTO option = new OptionDTO();
                option.setId(optionType.getId());
                option.setType(optionType.getType());
                option.setValues(values);
                options.add(option);
            }
        }

        response.setOptions(options);
        return this;
    }

    @Override
    public SearchResponseBuilder<T> setPage(long page, long pagesCount) {
        response.setPage(page);
        response.setPagesCount(pagesCount);
        return this;
    }

    @Override
    public T build() {
        return response;
    }

}
