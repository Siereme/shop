package app.model.product;

import app.model.product.description.ProductDescription;
import app.model.product.option.OptionValue;

import java.util.Set;

public interface IProduct {

    Long getId();

    Long getArticle();

    String getTitle();

    Double getPrice();

    String getImageLink();

    ProductDescription getDescription();

    Set<OptionValue> getOptions();

    void setId(Long id);

    void setArticle(Long article);

    void setTitle(String title);

    void setPrice(Double price);

    void setImageLink(String imageLink);

    void setDescription(ProductDescription description);

    void setOptions(Set<OptionValue> options);

}
