package app.repository.order;

import app.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select distinct o from Order o left join fetch o.user u left join fetch o.payment left join fetch o.orderItems c left join fetch c.product p left join fetch p.categories left join fetch p.description left join fetch p.options")
    List<Order> findAll();

    @Query(value = "select distinct o from Order o left join fetch o.user u left join fetch o.payment left join fetch o.orderItems c left join fetch c.product p left join fetch p.categories left join fetch p.description left join fetch p.options where o.id = :id")
    Order findByOrderId(Long id);
}
