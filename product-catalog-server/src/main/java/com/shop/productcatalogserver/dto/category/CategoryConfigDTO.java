package com.shop.productcatalogserver.dto.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryConfigDTO {

    long categoryId;
    boolean withParent;
    boolean withProducts;
    int page = 0;
    int pageSize = 24;

}
