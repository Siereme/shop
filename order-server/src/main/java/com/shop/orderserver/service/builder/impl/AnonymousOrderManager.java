package com.shop.orderserver.service.builder.impl;

import com.shop.orderserver.dto.OrderDTO;
import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.Order;
import com.shop.orderserver.service.builder.IOrderManager;
import com.shop.orderserver.utils.constant.UserRole;
import org.springframework.stereotype.Component;

@Component
public class AnonymousOrderManager implements IOrderManager<Order> {

    private final OrderBuilder constructor;

    public AnonymousOrderManager(OrderBuilder constructor) {
        this.constructor = constructor;
    }

    @Override
    public Order construct(OrderDTO orderDTO) {
        UserDTO user = orderDTO.getUser();
        constructor.create();
        constructor.setCustomerId(user.getId());
        constructor.setUserDetails(user);
        constructor.setLineItems(orderDTO.getLineItems());
        constructor.setReceiptDetail(orderDTO.getReceiptDetail());
        constructor.setPayment(orderDTO.getPayment().getId());
        constructor.setStatus();
        constructor.setTotal();
        constructor.clearShoppingCart(user.getId());
        return constructor.getOrder();
    }

    @Override
    public boolean findType(UserRole role) {
        return role == UserRole.ANONYMOUS;
    }
}
