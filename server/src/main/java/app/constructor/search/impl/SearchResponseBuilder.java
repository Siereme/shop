package app.constructor.search.impl;

import app.constructor.search.ISearchResponseBuilder;
import app.model.dto.search.ISearchResponse;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.OptionValueDTO;
import app.model.dto.search.PriceRangeDTO;
import app.model.product.Product;
import app.model.product.option.Option;
import app.model.product.option.OptionType;
import app.repository.product.ProductOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchResponseBuilder implements ISearchResponseBuilder {

    @Autowired
    private ProductOptionRepository optionRepo;
    private ISearchResponse response;
    private final List<OptionValueDTO> checkedValues = new ArrayList<>();

    @Override
    public SearchResponseBuilder create(ISearchResponse response){
        this.response = response;
        checkedValues.clear();
        return this;
    }

    @Override
    public SearchResponseBuilder setProducts(List<Product> products){
        response.setProducts(products);
        return this;
    }

    @Override
    public SearchResponseBuilder setPriceRange(){
        //Get range of product prices
        PriceRangeDTO priceRange = response.getProducts().stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            int minPrice = list.stream()
                                    .map(product -> product.getPrice().intValue())
                                    .min(Integer::compareTo).orElse(0);
                            int maxPrice = list.stream()
                                    .map(product -> product.getPrice().intValue())
                                    .max(Integer::compareTo).orElse(0);
                            return new PriceRangeDTO(minPrice, maxPrice);
                        }
                ));
        response.setPriceRange(priceRange);
        return this;
    }

    @Override
    public SearchResponseBuilder setCheckedOptions(List<OptionDTO> options){
        List<OptionValueDTO> values = options.stream()
                .map(OptionDTO::getValues)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        checkedValues.addAll(values);
        return this;
    }

    @Override
    public SearchResponseBuilder setOptions(){
        //Get options for products
        List<Option> productOptions = response.getProducts().stream()
                .map(Product::getOptions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        ////Group the type and values of options from a selection of products
        Map<Long, List<String>> optionValues = productOptions.stream()
                .collect(Collectors.groupingBy(
                        option -> option.getOptionType().getId(),
                        Collectors.mapping(Option::getValue, Collectors.toList())));

        ////Get type ids
        List<Long> typesIds = productOptions.stream()
                .map(option -> option.getOptionType().getId())
                .distinct().collect(Collectors.toList());

        ////Get types of options by ids
        List<OptionType> optionTypes = optionRepo.findAllOptionTypeByIds(typesIds).orElse(new ArrayList<>());

        ////Construct list of option dto
        List<OptionDTO> options = new ArrayList<>();
        for(OptionType optionType : optionTypes){
            OptionDTO option = new OptionDTO();

            option.setId(optionType.getId());
            option.setType(optionType.getType());

            List<OptionValueDTO> optionValue = optionType.getOptions().stream()
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
    public ISearchResponse build(){
        return response;
    }

}
