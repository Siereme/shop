package app.constructor.search;

import app.model.dto.search.ISearchResponse;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.PriceRangeDTO;
import app.model.product.Product;

import java.util.List;

public interface ISearchResponseBuilder<T extends ISearchResponse> {

    ISearchResponseBuilder<T> create(T response);

    ISearchResponseBuilder<T> setProducts(List<Product> products);

    ISearchResponseBuilder<T> setPriceRange(PriceRangeDTO priceRange);

    ISearchResponseBuilder<T> setCheckedOptions(List<OptionDTO> options);

    ISearchResponseBuilder<T> setOptions();

    T build();

}
