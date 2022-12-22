package com.shop.productcatalogserver.dto.category;

import com.shop.productcatalogserver.dto.product.IProductDTO;
import com.shop.productcatalogserver.model.category.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryResponse {

    private ICategoryDTO category;
    private Category parentCategory;
    private List<IProductDTO> products;
    private int pageCount;

}
