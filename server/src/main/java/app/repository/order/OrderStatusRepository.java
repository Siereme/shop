package app.repository.order;

import app.model.order.status.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    Optional<OrderStatus> findByType(String type);
}
