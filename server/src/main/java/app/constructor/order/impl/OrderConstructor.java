package app.constructor.order.impl;

import app.constructor.order.IOrderConstructor;
import app.model.order.Order;
import app.model.order.OrderProductItem;
import app.model.order.receipt.receiptDetail.ReceiptDetail;
import app.model.order.payment.Payment;
import app.model.order.receipt.Receipt;
import app.model.order.userDetails.OrderUserDetails;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import app.model.user.User;
import app.repository.order.PaymentRepository;
import app.repository.order.ReceiptRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.repository.user.UserRepository;
import app.service.shoppingCart.ShoppingCartService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Component
public class OrderConstructor implements IOrderConstructor<Order, User> {

    @Autowired
    protected UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private ReceiptRepository receiptRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    private Order order;
    private User user;

    @Override
    public void create() {
        this.order = new Order();
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = userRepo.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Order constructor - User doesn't exist"));
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public void setUserDetails(User user) {
        OrderUserDetails userDetails = new OrderUserDetails();
        userDetails.setName(user.getName());
        userDetails.setPatronymic(user.getPatronymic());
        userDetails.setSurname(user.getSurname());
        userDetails.setEmail(user.getEmail());
        userDetails.setPhone(user.getPhone());
        this.order.setUserDetails(userDetails);
    }


    @Override
    public void setReceiptDetail(ReceiptDetail receiptDetailDTO) {
        Receipt receipt = receiptRepo.findById(receiptDetailDTO.getReceipt().getId())
                .orElseThrow(() -> new UsernameNotFoundException("Order constructor - Receipt doesn't exist"));

        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setReceipt(receipt);
        receiptDetail.setAddress(receiptDetailDTO.getAddress());
        receiptDetail.setDate(receiptDetailDTO.getDate());
        this.order.setReceiptDetail(receiptDetail);
    }

    @Override
    public void setProductItems(long userId) {
        ShoppingCart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - Shopping cart is not found"));

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
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - Payment is not found"));
        order.setPayment(payment);
    }

    @Override
    public void setTotal() {
        Set<OrderProductItem> orderItems = this.order.getOrderItems();
        this.order.setTotal(cartService.calculateTotal(orderItems));
    }

    @Override
    public void refreshShoppingCart() {
        this.user.getShoppingCart().clear();
    }

    @Override
    public Order getOrder() {
        return this.order;
    }

}
