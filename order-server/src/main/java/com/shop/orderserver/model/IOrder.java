package com.shop.orderserver.model;

import com.shop.orderserver.model.userDetails.UserDetails;

public interface IOrder {
    void setOrderProduct(OrderLineItem orderLineItem);

    Long getId();

    UserDetails getUserDetails();

    OrderLineItems getOrderLineItems();

    com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail getReceiptDetail();

    com.shop.orderserver.model.payment.Payment getPayment();

    com.shop.orderserver.model.status.OrderStatus getStatus();

    Double getTotal();

    void setId(Long id);

    void setUserDetails(UserDetails userDetails);

    void setOrderLineItems(OrderLineItems orderLineItems);

    void setReceiptDetail(com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail receiptDetail);

    void setPayment(com.shop.orderserver.model.payment.Payment payment);

    void setStatus(com.shop.orderserver.model.status.OrderStatus status);

    void setTotal(Double total);
}
