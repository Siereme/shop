package com.shop.productcatalogserver.model.product;

import com.shop.productcatalogserver.model.product.description.ProductDescription;
import com.shop.productcatalogserver.model.product.option.OptionValue;

import java.util.Set;

public interface IProduct {

    Long getId();

    Long getSku();

    String getName();

    Double getPrice();

    String getImageLink();

    ProductDescription getDescription();

    Set<OptionValue> getOptions();

    void setId(Long id);

    void setSku(Long sku);

    void setName(String name);

    void setPrice(Double price);

    void setImageLink(String imageLink);

    void setDescription(ProductDescription description);

    void setOptions(Set<OptionValue> options);

}
