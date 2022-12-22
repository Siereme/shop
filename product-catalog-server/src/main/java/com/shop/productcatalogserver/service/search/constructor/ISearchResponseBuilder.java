package com.shop.productcatalogserver.service.search.constructor;

import com.shop.productcatalogserver.dto.search.ISearchResponse;
import com.shop.productcatalogserver.dto.search.OptionDTO;
import com.shop.productcatalogserver.dto.search.RangePriceDTO;
import com.shop.productcatalogserver.model.product.Product;
import com.shop.productcatalogserver.model.product.option.OptionValue;
import com.shop.productcatalogserver.service.search.constructor.impl.SearchResponseBuilder;

import java.util.List;

public interface ISearchResponseBuilder<T extends ISearchResponse> {

    ISearchResponseBuilder<T> create(T response);

    ISearchResponseBuilder<T> setProducts(List<Product> products);

    SearchResponseBuilder<T> setFullRangePrices(List<Double> prices);

    ISearchResponseBuilder<T> setRangePrice(RangePriceDTO priceRange);

    ISearchResponseBuilder<T> setCheckedOptions(List<OptionDTO> options);

    SearchResponseBuilder<T> setOptions(List<List<OptionValue>> options);

    SearchResponseBuilder<T> setPage(long page, long pagesCount);

    T build();

}
