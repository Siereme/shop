package app.service.order;

import app.model.dto.order.OrderDTO;
import app.model.order.Order;

public interface IOrderService<T extends Order> {

    T createOrder(OrderDTO orderDTO);

}
