package com.shop.productcatalogserver.dto.product;

import com.shop.productcatalogserver.model.product.description.ProductDescription;
import com.shop.productcatalogserver.model.product.option.OptionValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDTO {

    private Long sku;
    private String name;
    private Double price;
    private String imageLink;
    private ProductDescription description;
    private Set<OptionValue> options = new HashSet<>();
    private Set<Long> categoriesIds = new HashSet<>();
    private int count;

}
