package com.shop.productcatalogserver.model.product.description;

public interface IDescription {
    String getShortDescription();

    String getLongDescription();

    void setShortDescription(String shortDescription);

    void setLongDescription(String longDescription);
}
