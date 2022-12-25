package com.shop.productcatalogserver.repository.product;

import com.shop.productcatalogserver.dto.product.ILineItemDTO;
import com.shop.productcatalogserver.dto.product.IProductDTO;
import com.shop.productcatalogserver.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query(value = "select distinct p from Product p " +
            "left join fetch p.categories c " +
            "left join fetch c.categories " +
            "left join fetch p.options")
    List<Product> findAll();

    @Query(value = "select distinct p from Product p " +
            "left join fetch p.categories c " +
            "left join fetch c.categories " +
            "left join fetch p.options " +
            "where p.id = :id")
    Optional<Product> findById(Long id);

    @Query(value = "select distinct p from Product p " +
            "left join fetch p.categories c " +
            "left join fetch c.categories " +
            "left join fetch p.options " +
            "where p.id in :ids")
    List<Product> findAllById(Iterable<Long> ids);

    @Query(value = "select distinct p from Product p " +
            "left join fetch p.categories c " +
            "left join fetch c.products " +
            "left join fetch p.options " +
            "where p.id = :id")
    Optional<Product> findByIdWithCategoryProducts(Long id);

    @Query(value = "select distinct p.id from Product p")
    Optional<List<Long>> findPopularIds(PageRequest of);

    @Query(value = "select distinct p from Product p " +
            "left join fetch p.categories c " +
            "left join fetch p.options " +
            "where c.path like :path%")
    Optional<List<Product>> findByPath(String path);

    @EntityGraph(attributePaths = {"categories", "options"})
    @Query(value = "select distinct p from Product p " +
            "left join p.categories c " +
            "left join p.options " +
            "where c.path like :path%")
    Page<IProductDTO> findByPathWithCategoryIds(String path, Pageable page);

    ILineItemDTO findBySku(Long sku);

    @Query(value = "select distinct p from Product p " +
            "where p.sku in :skus")
    List<ILineItemDTO> findAllBySku(List<Long> skus);
}
