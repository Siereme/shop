package com.shop.orderserver.dto;

import com.shop.orderserver.model.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponse {

    String token;
    Order order;

    public OrderResponse(Order order) {
        this.order = order;
    }

    public OrderResponse(String token, Order order) {
        this.token = token;
        this.order = order;
    }

}
