package com.shop.productcatalogserver.dto.category;

import com.shop.productcatalogserver.model.category.ICategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryRequest implements ICategory {

    private Long id;
    private String name;
    private String imageLink;
    private Long parentId;
    private List<Long> subCategoriesIds;

}
