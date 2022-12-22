package com.shop.orderserver.service.builder;

import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.IOrder;
import com.shop.orderserver.model.OrderLineItem;
import com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail;

import java.util.List;

public interface IOrderBuilder<R extends IOrder, U extends UserDTO> {

    void create();

    void setUserDetails(U user);

    void setReceiptDetail(ReceiptDetail receiptDetail);

    void setLineItems(List<OrderLineItem> lineItems);

    void setPayment(long paymentId);

    void setStatus();

    void setStatus(String statusType);

    void setTotal();

    R getOrder();

    void clearShoppingCart(long customerId);

}
