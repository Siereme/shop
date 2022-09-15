package app.constructor.order;

import app.model.dto.order.OrderDTO;
import app.model.order.IOrder;
import app.utils.constants.user.UserRole;

public interface IOrderManager<T extends IOrder> {

    T construct(OrderDTO orderDTO);

    boolean findType(UserRole role);

}
