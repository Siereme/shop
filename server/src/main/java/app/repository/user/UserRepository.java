package app.repository.user;

import app.model.order.Order;
import app.model.shoppingCart.ShoppingCart;
import app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select distinct u from User u " +
            "left join fetch u.role r left join fetch r.permissions " +
            "left join fetch r.users where u.id = :id")
    Optional<User> findById(Long id);

    @Query(value = "select distinct u from User u " +
            "left join fetch u.role r left join fetch r.permissions " +
            "left join fetch r.users where u.email = :email")
    Optional<User> findByEmail(String email);

    @Query(value = "select distinct o from User u " +
            "left join u.orders o " +
            "left join fetch o.userDetails oud left join fetch oud.delivery " +
            "left join fetch o.orderItems oi left join fetch oi.product op " +
            "left join fetch op.description left join fetch op.options " +
            "left join fetch o.payment " +
            "where u.id = :id")
    Optional<List<Order>> findAllOrdersById(Long id);

    @Query(value = "select distinct s from User u " +
            "left join u.shoppingCart s " +
            "left join fetch s.cartItems si left join fetch si.product op " +
            "left join fetch op.description left join fetch op.options " +
            "where u.id = :id")
    Optional<ShoppingCart> findShoppingCartById(Long id);

}
