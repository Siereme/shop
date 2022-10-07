package app.constructor.search;

import app.model.dto.search.ISearchResponse;
import app.model.product.Product;

import java.util.List;

public interface ISearchResponseConstructor<T extends ISearchResponse> {
    T construct(List<Product> products);
}
