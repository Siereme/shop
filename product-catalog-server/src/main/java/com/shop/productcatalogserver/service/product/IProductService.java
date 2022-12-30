package com.shop.productcatalogserver.service.product;

import com.shop.productcatalogserver.dto.product.ProductDTO;
import com.shop.productcatalogserver.dto.product.ProductsExistsDTO;
import com.shop.productcatalogserver.model.product.Product;

import java.util.List;

public interface IProductService<T extends Product> {

    T addProduct(ProductDTO productDTO);

    void deleteById(Long id);

    List<T> getPopular();

    List<T> findByCategoryId(Long id);

    List<T> findByCategoryPath(String path, int depth);

    ProductsExistsDTO isExists(List<Long> sku);
}
