package app.constructor.search;

import app.constructor.search.impl.SearchResponseConstructor;
import app.model.dto.search.ISearchResponse;
import app.model.product.Product;

import java.util.List;

public interface ISearchResponseConstructor<T extends ISearchResponse> {
    SearchResponseConstructor<T> setProducts(List<Product> products);

    int getMinPrice();

    int getMaxPrice();

    SearchResponseConstructor<T> setPriceRange();

    SearchResponseConstructor<T> setOptions();

    SearchResponseConstructor<T> create();

    T build();

    T construct(List<Product> products);
}
