package com.shop.productcatalogserver.dto.main;

import com.shop.productcatalogserver.model.category.Category;
import com.shop.productcatalogserver.model.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MainResponse {

    private List<Category> categories;
    private List<Product> productsPopular;

}
