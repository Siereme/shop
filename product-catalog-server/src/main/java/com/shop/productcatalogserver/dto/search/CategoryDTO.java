package com.shop.productcatalogserver.dto.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDTO {

    long id;
    String path;
    int depth = 1;
    boolean withParent;

}
