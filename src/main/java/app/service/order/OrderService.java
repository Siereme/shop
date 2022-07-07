package app.service.order;

import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.order.OrderProduct;
import app.model.order.Payment;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartItem;
import app.model.user.User;
import app.repository.order.OrderRepository;
import app.repository.payment.PaymentRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.service.shoppingCart.ShoppingCartService;
import app.service.user.UserService;
import app.utils.constants.UserVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private ShoppingCartRepository cartRepo;
    @Autowired private PaymentRepository paymentRepo;
    @Autowired private ShoppingCartService cartService;
    @Autowired private UserService userService;


    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        User user = userService.findUser(orderDTO.getUser());
        ShoppingCart cart = cartRepo.findByUserId(user.getId());
        if(cart == null) return;

        if(Objects.equals(user.getStatus(), UserVariables.STATUS_UNVERIFIED)){
            user = userService.updateUser(user);
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        Set<ShoppingCartItem> cartItems = cart.getCartItems();
        for (ShoppingCartItem cartItem : cartItems) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItem.getProduct());
            orderProduct.setCount(cartItem.getCount());
            order.addOrderProduct(orderProduct);
        }
        Payment payment = paymentRepo.findById(orderDTO.getPayment().getId()).orElse(null);
        order.setPayment(payment);

        orderRepo.saveAndFlush(order);
        cartService.refreshShoppingCart(user.getId());
    }

    @Transactional
    public void removeOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }

    @Transactional
    public void removeAll() {
        orderRepo.deleteAll();
    }
}
