package app.constructor.order;

import app.model.dto.order.OrderDTO;
import app.model.order.IOrder;
import app.model.order.userDetails.IOrderUserDetails;

public interface IOrderManager<T extends IOrder<? extends IOrderUserDetails>> {

    T construct(OrderDTO orderDTO);

}
