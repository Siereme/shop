package app.model.product;

import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;

import java.util.Set;

public interface IProduct {

    Long getId();

    Long getArticle();

    String getName();

    Double getPrice();

    String getImageLink();

    ProductDescription getDescription();

    Set<ProductOption> getOptions();

    void setId(Long id);

    void setArticle(Long article);

    void setName(String name);

    void setPrice(Double price);

    void setImageLink(String imageLink);

    void setDescription(ProductDescription description);

    void setOptions(java.util.Set<ProductOption> options);

}
