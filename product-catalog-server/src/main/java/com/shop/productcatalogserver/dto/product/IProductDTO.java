package com.shop.productcatalogserver.dto.product;

import com.shop.productcatalogserver.model.product.IProduct;

import java.util.List;

public interface IProductDTO extends IProduct {

    List<CategoryId> getCategories();

    interface CategoryId {
        Long getId();
    }
}
