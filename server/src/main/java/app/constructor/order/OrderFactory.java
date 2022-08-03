package app.constructor.order;

import app.constructor.order.impl.AnonymousOrderConstructor;
import app.constructor.order.impl.DefaultOrderConstructor;
import app.exception.UnknownConstructorTypeException;
import app.model.order.Order;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    @Autowired
    private ApplicationContext context;

    public IOrderConstructor<Order, User> getFactory(UserRole role) {
        if (role == UserRole.ANONYMOUS) {
            return context.getBean(AnonymousOrderConstructor.class);
        } else if (role == UserRole.USER || role == UserRole.ADMIN){
            return context.getBean(DefaultOrderConstructor.class);
        } else {
            throw new UnknownConstructorTypeException();
        }
    }
}
