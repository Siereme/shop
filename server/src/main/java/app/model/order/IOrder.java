package app.model.order;

import app.model.order.payment.Payment;
import app.model.order.userDetails.IOrderUserDetails;
import app.model.order.userDetails.OrderUserDetails;

import java.util.Set;

public interface IOrder {
    void setOrderProduct(OrderProductItem orderProductItem);

    Long getId();

    OrderUserDetails getUserDetails();

    Set<OrderProductItem> getOrderItems();

    Payment getPayment();

    Double getTotal();

    void setId(Long id);

    void setUserDetails(OrderUserDetails user);

    void setOrderItems(Set<OrderProductItem> orderItems);

    void setPayment(Payment payment);

    void setTotal(Double total);
}
