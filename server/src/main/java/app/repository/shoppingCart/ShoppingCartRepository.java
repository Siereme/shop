package app.repository.shoppingCart;

import app.model.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "select distinct s from ShoppingCart s left join fetch s.cartItems c left join fetch c.product p left join fetch p.categories left join fetch p.description left join fetch p.options where s.id = :id")
    Optional<ShoppingCart> findByCartId(Long id);

    @Query(value = "select distinct s from User u " +
            "left join u.shoppingCart s " +
            "left join fetch s.cartItems si left join fetch si.product op " +
            "left join fetch op.description left join fetch op.options " +
            "where u.id = :id")
    Optional<ShoppingCart> findByUserId(Long id);

}
