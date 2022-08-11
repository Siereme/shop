package app.constructor.order;

import app.constructor.order.impl.AnonymousOrderManager;
import app.constructor.order.impl.DefaultOrderManager;
import app.exception.UnknownConstructorTypeException;
import app.model.order.Order;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    @Autowired
    private ApplicationContext context;

    public IOrderManager<Order> getFactory(UserRole role) {
        if (role == UserRole.ANONYMOUS) {
            return context.getBean(AnonymousOrderManager.class);
        } else if (role == UserRole.USER || role == UserRole.ADMIN) {
            return context.getBean(DefaultOrderManager.class);
        } else {
            throw new UnknownConstructorTypeException();
        }
    }

}
