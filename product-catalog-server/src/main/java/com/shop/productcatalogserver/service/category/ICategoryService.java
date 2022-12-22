package com.shop.productcatalogserver.service.category;

import com.shop.productcatalogserver.dto.category.CategoryRequest;
import com.shop.productcatalogserver.model.category.ICategory;

public interface ICategoryService<T extends ICategory> {

    T addCategory(CategoryRequest categoryRequest);

    T getByPathAndDepth(String path, int depth);

}
