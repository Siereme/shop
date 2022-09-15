package app.constructor.order;

import app.exception.UnknownConstructorTypeException;
import app.model.order.Order;
import app.utils.constants.user.UserRole;
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
