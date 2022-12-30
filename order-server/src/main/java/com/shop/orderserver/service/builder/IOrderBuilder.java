package com.shop.orderserver.service.builder;

import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.IOrder;
import com.shop.orderserver.model.OrderLineItem;
import com.shop.orderserver.model.OrderLineItems;
import com.shop.orderserver.model.payment.Payment;
import com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail;
import com.shop.orderserver.model.status.OrderStatus;
import com.shop.orderserver.model.userDetails.UserDetails;
import com.shop.orderserver.utils.constant.OrderStatuses;

import java.util.List;

public interface IOrderBuilder<R extends IOrder, U extends UserDTO> {

    UserDetails getUserDetails(U user);

    ReceiptDetail getReceiptDetail(ReceiptDetail receiptDetail);

    OrderLineItems getLineItems(List<OrderLineItem> lineItemsIds);

    Payment getPayment(Payment payment);

    OrderStatus getStatus(Payment payment);

    OrderStatus getStatus(OrderStatuses statusType);

    double getTotal(List<OrderLineItem> lineItems);

    void clearShoppingCart(long customerId);

}
