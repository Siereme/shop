package app.constructor.search.impl;

import app.constructor.search.ISearchResponseConstructor;
import app.model.dto.search.ISearchResponse;
import app.model.dto.search.PriceRangeDTO;
import app.model.product.Product;
import app.model.product.option.ProductOption;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SearchResponseConstructor<T extends ISearchResponse> implements ISearchResponseConstructor<T> {

    private final Supplier<T> supplier;
    private T response;

    public SearchResponseConstructor(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public SearchResponseConstructor<T> setProducts(List<Product> products) {
        this.response.setProducts(products);
        return this;
    }

    @Override
    public int getMinPrice() {
        return response.getProducts().stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElseGet(Product::new)
                .getPrice().intValue();
    }

    @Override
    public int getMaxPrice() {
        return response.getProducts().stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElseGet(Product::new)
                .getPrice().intValue();
    }

    @Override
    public SearchResponseConstructor<T> setPriceRange() {
        this.response.setPriceRange(new PriceRangeDTO(getMinPrice(), getMaxPrice()));
        return this;
    }

    @Override
    public SearchResponseConstructor<T> setOptions() {
        Map<String, Set<String>> options = response.getProducts().stream()
                .map(Product::getOptions)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(ProductOption::getName, Collectors.mapping(ProductOption::getValue, Collectors.toSet())));
        this.response.setOptions(options);
        return this;
    }

    @Override
    public SearchResponseConstructor<T> create() {
        this.response = supplier.get();
        return this;
    }

    @Override
    public T build() {
        return this.response;
    }

    @Override
    public T construct(List<Product> products) {
        create();
        setProducts(products);
        setPriceRange();
        setOptions();
        return build();
    }

}
