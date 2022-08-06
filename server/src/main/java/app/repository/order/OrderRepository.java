package app.repository.order;

import app.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select distinct o from Order o left join fetch o.userDetails u left join fetch o.payment left join fetch o.orderItems c left join fetch c.product p left join fetch p.categories left join fetch p.description left join fetch p.options")
    List<Order> findAll();

    @Query(value = "select distinct o from Order o left join fetch o.userDetails u left join fetch o.payment left join fetch o.orderItems c left join fetch c.product p left join fetch p.categories left join fetch p.description left join fetch p.options where u.id = :id")
    Optional<List<Order>> findAllByUserDetailsId(Long id);

    @Query(value = "select distinct o from Order o left join fetch o.userDetails u left join fetch o.payment left join fetch o.orderItems c left join fetch c.product p left join fetch p.categories left join fetch p.description left join fetch p.options where o.id = :id")
    Optional<Order> findByOrderId(Long id);
}
