package com.shop.productcatalogserver.dto.product;

public interface ILineItemDTO {
    long getArticle();

    String getName();

    String getImageLink();

    Double getPrice();

    void setArticle(long article);

    void setName(String name);

    void setImageLink(String imageLink);

    void setPrice(Double price);
}
