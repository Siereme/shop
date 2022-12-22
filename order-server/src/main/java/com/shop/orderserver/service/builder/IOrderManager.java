package com.shop.orderserver.service.builder;

import com.shop.orderserver.dto.OrderDTO;
import com.shop.orderserver.model.IOrder;
import com.shop.orderserver.utils.constant.UserRole;

public interface IOrderManager<T extends IOrder> {

    T construct(OrderDTO orderDTO);

    boolean findType(UserRole role);

}
