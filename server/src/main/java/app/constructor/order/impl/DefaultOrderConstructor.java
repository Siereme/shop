package app.constructor.order.impl;

import app.constructor.order.AbstractOrderConstructor;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrderConstructor extends AbstractOrderConstructor {

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = orderDTO.getUser();
        setUser(user);
        setProductItems(user.getId());
        setPayment(orderDTO.getPayment().getId());
        setTotal();
        return super.order;
    }
}
