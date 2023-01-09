package com.shop.orderserver.dto;

import com.shop.orderserver.model.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponse {

    Order order;

    public OrderResponse(Order order) {
        this.order = order;
    }

}
