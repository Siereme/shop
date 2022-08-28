package app.repository.shoppingCart;

import app.model.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "select distinct s from ShoppingCart s " +
            "left join fetch s.cartItems si left join fetch si.product op " +
            "left join fetch op.description left join fetch op.options")
    List<ShoppingCart> findAll();

    @Query(value = "select distinct s from User u " +
            "left join u.shoppingCart s " +
            "left join fetch s.cartItems si left join fetch si.product op " +
            "left join fetch op.description left join fetch op.options " +
            "where u.id = :id")
    Optional<ShoppingCart> findByUserId(Long id);

}
