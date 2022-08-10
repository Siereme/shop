package app.service.order;

import app.constructor.order.IOrderConstructor;
import app.constructor.order.IOrderManager;
import app.constructor.order.OrderFactory;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.user.User;
import app.repository.order.OrderRepository;
import app.service.shoppingCart.ShoppingCartService;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService implements IOrderService<Order> {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private OrderFactory orderFactory;


    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        UserRole role = UserRole.valueOf(orderDTO.getUser().getRole().getName());
        IOrderManager<Order> constructor = orderFactory.getFactory(role);
        Order order = constructor.construct(orderDTO);

        return orderRepo.save(order);
    }

}
