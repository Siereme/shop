package app.repository.product;

import app.model.product.Product;
import app.model.shoppingCart.ShoppingCartProductItem;
import org.springframework.data.domain.PageRequest;
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
    Optional<List<Product>> findByPathInDepth(String path);

    @Query(value = "select distinct count(oi) from Order o " +
            "left join o.orderItems oi " +
            "left join oi.product p " +
            "where p.id = :id")
    Optional<Integer> findCountOrderItems(Long id);

    @Query(value = "select distinct ci from ShoppingCart sc " +
            "left join sc.cartItems ci " +
            "left join fetch ci.product p " +
            "where p.id = :id")
    Optional<List<ShoppingCartProductItem>> findCartItems(Long id);
}
