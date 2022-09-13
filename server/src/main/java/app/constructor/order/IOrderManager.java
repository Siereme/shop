package app.constructor.order;

import app.model.dto.order.OrderDTO;
import app.model.order.IOrder;

public interface IOrderManager<T extends IOrder> {

    T construct(OrderDTO orderDTO);

}
