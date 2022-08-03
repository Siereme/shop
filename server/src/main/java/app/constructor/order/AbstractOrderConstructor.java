package app.constructor.order;

import app.exception.EntityNotFoundException;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.order.OrderProductItem;
import app.model.order.payment.Payment;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import app.model.user.User;
import app.repository.order.PaymentRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.repository.user.UserRepository;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

public abstract class AbstractOrderConstructor implements IOrderConstructor<Order, User>{

    @Autowired
    protected UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    protected Order order;

    public AbstractOrderConstructor() {
        this.order = new Order();
    }

    @Override
    public void setUser(User user) {
        User orderUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        this.order.setUser(orderUser);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public void setProductItems(long userId) {
        ShoppingCart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));

        Set<ShoppingCartProductItem> cartItems = cart.getCartItems();
        for (ShoppingCartProductItem cartItem : cartItems) {
            OrderProductItem orderProductItem = new OrderProductItem();
            orderProductItem.setOrder(this.order);
            orderProductItem.setProduct(cartItem.getProduct());
            orderProductItem.setCount(cartItem.getCount());
            this.order.setOrderProduct(orderProductItem);
        }
    }

    @Override
    public void setPayment(long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment is not found"));
        order.setPayment(payment);
    }

    @Override
    public void setTotal() {
        double total = this.order.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getPrice() * orderItem.getCount())
                .reduce(0d, Double::sum);
        this.order.setTotal(total);
    }

    @Override
    public abstract Order createOrder(OrderDTO orderDTO);
}
