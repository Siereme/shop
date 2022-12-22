package com.shop.productcatalogserver.service.search;

import com.shop.productcatalogserver.dto.search.ISearchDTO;
import com.shop.productcatalogserver.dto.search.ISearchResponse;

public interface ISearchService<T extends ISearchDTO> {

    ISearchResponse search(T config);

    ISearchResponse searchByOptions(T config);

}
