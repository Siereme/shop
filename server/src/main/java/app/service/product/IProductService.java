package app.service.product;

import app.model.product.Product;

import java.util.List;

public interface IProductService<T extends Product> {

    T addProduct(T productDTO);

    void deleteProduct(Long id);

    List<T> getPopular();

    List<T> findByCategoryId(Long id);
}
