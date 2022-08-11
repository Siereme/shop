package app.service.product;

import app.model.dto.product.ProductDTO;
import app.model.product.Product;

import java.util.List;

public interface IProductService<T extends Product> {

    T addProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    List<T> getPopular();

    List<T> findByCategoryId(Long id);
}
