package com.shop.productcatalogserver.model.product;

import com.shop.productcatalogserver.model.product.description.ProductDescription;
import com.shop.productcatalogserver.model.product.option.OptionValue;

import java.util.Set;

public interface IProduct {

    Long getId();

    Long getArticle();

    String getName();

    Double getPrice();

    String getImageLink();

    ProductDescription getDescription();

    Set<OptionValue> getOptions();

    void setId(Long id);

    void setArticle(Long article);

    void setName(String name);

    void setPrice(Double price);

    void setImageLink(String imageLink);

    void setDescription(ProductDescription description);

    void setOptions(Set<OptionValue> options);

}
