package app.model.dto.product;

import app.model.product.IProduct;

import java.util.List;

public interface IProductDTO extends IProduct {

    List<CategoryId> getCategories();

    interface CategoryId {
        Long getId();
    }
}
