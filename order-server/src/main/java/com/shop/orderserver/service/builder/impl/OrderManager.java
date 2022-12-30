package com.shop.orderserver.service.builder.impl;

import com.shop.orderserver.dto.OrderDTO;
import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.Order;
import com.shop.orderserver.model.OrderLineItem;
import com.shop.orderserver.model.payment.Payment;
import com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail;
import com.shop.orderserver.service.builder.IOrderManager;
import com.shop.orderserver.utils.constant.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManager implements IOrderManager<Order> {

    private final OrderBuilder builder;

    @Override
    public Order construct(OrderDTO orderDTO) {
        Order order = new Order();
        UserDTO user = orderDTO.getUser();
        Payment payment = orderDTO.getPayment();
        ReceiptDetail receiptDetail = orderDTO.getReceiptDetail();
        List<OrderLineItem> lineItems = orderDTO.getLineItems();

        order.setCustomerId(user.getId());
        order.setUserDetails(builder.getUserDetails(user));
        order.setReceiptDetail(builder.getReceiptDetail(receiptDetail));
        order.setOrderLineItems(builder.getLineItems(lineItems));
        order.setPayment(builder.getPayment(payment));
        order.setStatus(builder.getStatus(payment));
        order.setTotal(builder.getTotal(lineItems));
        builder.clearShoppingCart(order.getCustomerId());

        return order;
    }

    @Override
    public boolean findType(UserRole role) {
        return role == UserRole.USER || role == UserRole.ADMIN || role == UserRole.ANONYMOUS;
    }
}
