package app.constructor.order;

import app.model.dto.order.OrderDTO;
import app.model.order.IOrder;
import app.model.user.IUser;

public interface IOrderConstructor<R extends IOrder, U extends IUser> {

    void setUser(U user);

    U updateUser(U user);

    void setProductItems(long userId);

    void setPayment(long paymentId);

    void setTotal();

    R createOrder(OrderDTO orderDTO);

}
