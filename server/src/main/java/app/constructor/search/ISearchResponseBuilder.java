package app.constructor.search;

import app.constructor.search.impl.SearchResponseBuilder;
import app.model.dto.search.ISearchResponse;
import app.model.dto.search.OptionDTO;
import app.model.dto.search.RangePriceDTO;
import app.model.product.Product;

import java.util.List;

public interface ISearchResponseBuilder<T extends ISearchResponse> {

    ISearchResponseBuilder<T> create(T response);

    ISearchResponseBuilder<T> setProducts(List<Product> products);

    SearchResponseBuilder<T> setFullRangePrices(List<Double> prices);

    ISearchResponseBuilder<T> setRangePrice(RangePriceDTO priceRange);

    ISearchResponseBuilder<T> setCheckedOptions(List<OptionDTO> options);

    ISearchResponseBuilder<T> setOptions();

    SearchResponseBuilder<T> setPage(long page, long pagesCount);

    T build();

}
