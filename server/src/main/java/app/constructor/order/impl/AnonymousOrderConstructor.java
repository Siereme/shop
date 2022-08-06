package app.constructor.order.impl;

import app.constructor.order.AbstractOrderConstructor;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class AnonymousOrderConstructor extends AbstractOrderConstructor {

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = updateUser(orderDTO.getUser());
        init();
        setUser(user);
        setUserDetails(user);
        setProductItems(user.getId());
        setPayment(orderDTO.getPayment().getId());
        setTotal();
        getUser().setOrder(getOrder());
        refreshShoppingCart();
        return getOrder();
    }

}
