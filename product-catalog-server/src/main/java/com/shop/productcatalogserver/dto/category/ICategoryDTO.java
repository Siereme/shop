package com.shop.productcatalogserver.dto.category;

public interface ICategoryDTO {

    Long getId();

    String getName();

    String getImageLink();

    String getPath();

    int getDepth();

}
