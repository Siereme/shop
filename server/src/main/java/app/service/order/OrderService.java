package app.service.order;

import app.exception.EntityNotFoundException;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.order.OrderProduct;
import app.model.order.Payment;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartItem;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.utils.constants.user.UserStatus;
import app.repository.order.OrderRepository;
import app.repository.payment.PaymentRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.service.shoppingCart.ShoppingCartService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
public class OrderService implements IOrderService<Order> {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private PaymentRepository paymentRepo;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;


    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepo.findById(orderDTO.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));

        ShoppingCart cart = cartRepo.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));

        if (Objects.equals(user.getStatus(), UserStatus.ANONYMOUS)) {
            user = userService.updateUser(orderDTO.getUser());
        }

        Order order = new Order();
        order.setUser(cart.getUser());

        Set<ShoppingCartItem> cartItems = cart.getCartItems();
        double total = 0d;
        for (ShoppingCartItem cartItem : cartItems) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItem.getProduct());
            orderProduct.setCount(cartItem.getCount());
            order.addOrderProduct(orderProduct);
            total += orderProduct.getProduct().getPrice() * orderProduct.getCount();
        }

        order.setTotal(total);

        Payment payment = paymentRepo.findById(orderDTO.getPayment().getId()).orElse(null);
        order.setPayment(payment);

        cartService.refreshShoppingCart(user.getId());
        return orderRepo.saveAndFlush(order);
    }

}
