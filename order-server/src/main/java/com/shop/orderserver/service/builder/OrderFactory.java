package com.shop.orderserver.service.builder;

import com.shop.orderserver.exception.UnknownConstructorTypeException;
import com.shop.orderserver.model.Order;
import com.shop.orderserver.utils.constant.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderFactory {

    @Autowired
    private Set<IOrderManager<Order>> managers;

    public IOrderManager<Order> getFactory(UserRole role) {
        return managers.stream()
                .filter(manager -> manager.findType(role))
                .findFirst().orElseThrow(UnknownConstructorTypeException::new);
    }

}
