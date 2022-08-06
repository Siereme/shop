package app.service.shoppingCart;

import app.exception.EntityNotFoundException;
import app.model.product.Product;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import app.model.user.User;
import app.repository.product.ProductRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepo;


    @Transactional
    public void setCartItem(Long userId, Long productId, int count) {
        ShoppingCart cart = userRepo.findShoppingCartById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));

        for (ShoppingCartProductItem cartItem : cart.getCartItems()) {
            if (Objects.equals(cartItem.getProduct().getId(), productId)) {
                cartItem.setCount(count);
                calculateTotal(cart);
                return;
            }
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));

        ShoppingCartProductItem cartItem = new ShoppingCartProductItem();
        cartItem.setShoppingCart(cart);
        cartItem.setProduct(product);
        cartItem.setCount(count);
        cart.setCartItem(cartItem);
        cart.setCount(cart.getCartItems().size());
        calculateTotal(cart);
    }

    private void calculateTotal(ShoppingCart cart){
        double total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice() * item.getCount())
                .reduce(0d, Double::sum);
        cart.setTotal(total);
    }

    @Transactional
    public void removeCartItem(Long userId, Long productId) {
        ShoppingCart cart = userRepo.findShoppingCartById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
        cart.getCartItems().removeIf(cartItem -> Objects.equals(cartItem.getProduct().getId(), productId));
    }

    @Transactional
    public void refreshShoppingCart(Long userId) {
        ShoppingCart cart = userRepo.findShoppingCartById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
        cart.getCartItems().clear();
        cart.setCount(0);
        cart.setTotal(0d);
    }
}
