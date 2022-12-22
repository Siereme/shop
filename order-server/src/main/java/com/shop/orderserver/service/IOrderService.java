package com.shop.orderserver.service;

import com.shop.orderserver.dto.OrderDTO;
import com.shop.orderserver.model.Order;

public interface IOrderService<T extends Order> {

    T createOrder(OrderDTO orderDTO);

}
