package com.shop.productcatalogserver.model.product.description;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDescription implements IDescription {

    @FullTextField
    private String shortDescription;

    @FullTextField
    private String longDescription;

}
