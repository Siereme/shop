package app.constructor.order;

import app.model.order.IOrder;
import app.model.order.Order;
import app.model.order.delivery.Delivery;
import app.model.order.userDetails.IOrderUserDetails;
import app.model.user.IUser;

public interface IOrderConstructor<R extends IOrder<? extends IOrderUserDetails>, U extends IUser> {

    void create();

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

}
