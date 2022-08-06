package app.model.order;

import app.model.order.payment.Payment;
import app.model.order.userDetails.IOrderUserDetails;

import java.util.Set;

public interface IOrder<T extends IOrderUserDetails> {
    void setOrderProduct(OrderProductItem orderProductItem);

    Long getId();

    T getUserDetails();

    Set<OrderProductItem> getOrderItems();

    Payment getPayment();

    Double getTotal();

    void setId(Long id);

    void setUserDetails(T user);

    void setOrderItems(Set<OrderProductItem> orderItems);

    void setPayment(Payment payment);

    void setTotal(Double total);
}
