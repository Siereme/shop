package app.constructor.order.impl;

import app.constructor.order.IOrderManager;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class AnonymousOrderManager implements IOrderManager<Order> {

    private final OrderConstructor constructor;

    public AnonymousOrderManager(OrderConstructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public Order construct(OrderDTO orderDTO) {
        User user = constructor.updateUser(orderDTO.getUser());
        constructor.create();
        constructor.setUser(user);
        constructor.setUserDetails(user);
        constructor.setProductItems(user.getId());
        constructor.setPayment(orderDTO.getPayment().getId());
        constructor.setTotal();
        Order order = constructor.getOrder();
        constructor.getUser().setOrder(order);
        constructor.refreshShoppingCart();
        return order;
    }
}
