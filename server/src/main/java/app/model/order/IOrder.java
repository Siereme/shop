package app.model.order;

import app.model.order.payment.Payment;
import app.model.user.User;

import java.util.Set;

public interface IOrder {
    void setOrderProduct(OrderProductItem orderProductItem);

    Long getId();

    User getUser();

    Set<OrderProductItem> getOrderItems();

    Payment getPayment();

    Double getTotal();

    void setId(Long id);

    void setUser(User user);

    void setOrderItems(Set<OrderProductItem> orderItems);

    void setPayment(Payment payment);

    void setTotal(Double total);
}
