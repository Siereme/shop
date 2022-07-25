package app.repository.product;

import app.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query(value = "select distinct p from Product p left join fetch p.categories left join fetch p.description left join fetch p.options")
    List<Product> findAll();

    @Query(value = "select distinct p from Product p left join fetch p.categories left join fetch p.description left join fetch p.options where p.id = :id")
    Optional<Product> findById(Long id);

    @Query(value = "select distinct p from Product p left join fetch p.categories left join fetch p.description left join fetch p.options where p.id in :ids")
    List<Product> findAllById(Iterable<Long> ids);

    @Query(value = "SELECT product.product_id FROM product LEFT JOIN order_product_items ON product.product_id=order_product_items.order_product_id GROUP BY product.product_id ORDER BY COUNT(order_product_items.order_product_id) * order_product_items.count desc", nativeQuery = true)
    List<Long> findPopularIds();

    @Query(value = "select distinct p from Product p left join fetch p.categories c left join fetch p.description left join fetch p.options where :id in (c.id)")
    List<Product> findByCategoryId(Long id);
}
