package app.repository.order;

import app.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select distinct o from Order o " +
            "left join fetch o.userDetails " +
            "left join fetch o.receiptDetail rd " +
            "left join fetch rd.receipt " +
            "left join fetch o.payment " +
            "left join fetch o.status " +
            "left join fetch o.orderItems c " +
            "left join fetch c.product p " +
            "left join fetch p.categories " +
            "left join fetch p.description " +
            "left join fetch p.options")
    List<Order> findAll();

    @Query(value = "select distinct o from User u " +
            "left join u.orders o " +
            "left join fetch o.userDetails " +
            "left join fetch o.orderItems oi left join fetch oi.product op " +
            "left join fetch op.description left join fetch op.options " +
            "left join fetch o.receiptDetail rd " +
            "left join fetch rd.receipt " +
            "left join fetch o.payment " +
            "left join fetch o.status " +
            "where u.id = :id")
    List<Order> findAllByUserId(Long id);

}
