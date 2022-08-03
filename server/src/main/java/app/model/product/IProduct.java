package app.model.product;

import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;

public interface IProduct {
    Long getId();

    Long getArticle();

    String getName();

    Double getPrice();

    String getImageLink();

    ProductDescription getDescription();

    java.util.Set<ProductOption> getOptions();

    java.util.Set<app.model.category.Category> getCategories();

    void setId(Long id);

    void setArticle(Long article);

    void setName(String name);

    void setPrice(Double price);

    void setImageLink(String imageLink);

    void setDescription(ProductDescription description);

    void setOptions(java.util.Set<ProductOption> options);

    void setCategories(java.util.Set<app.model.category.Category> categories);
}
