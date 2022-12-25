package com.shop.productcatalogserver.dto.product;

public interface ILineItemDTO {
    long getSku();

    String getName();

    String getImageLink();

    Double getPrice();

    void setSku(long article);

    void setName(String name);

    void setImageLink(String imageLink);

    void setPrice(Double price);
}
