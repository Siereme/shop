package app.constructor.order;

import app.model.dto.order.OrderDTO;
import app.model.order.IOrder;
import app.model.order.Order;
import app.model.order.delivery.Delivery;
import app.model.user.IUser;

public interface IOrderConstructor<R extends IOrder, U extends IUser> {

    void init();

    U getUser();

    void setUser(U user);

    U updateUser(U user);

    void setUserDetails(U user);

    void setDelivery(Delivery delivery);

    void setProductItems(long userId);

    void setPayment(long paymentId);

    void setTotal();

    Order getOrder();

    void refreshShoppingCart();

    R createOrder(OrderDTO orderDTO);

}
