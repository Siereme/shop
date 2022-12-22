package com.shop.productcatalogserver.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.productcatalogserver.model.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchResponse implements ISearchResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Product> products;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    RangePriceDTO rangePrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<OptionDTO> options;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    long page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    long pagesCount;

}
