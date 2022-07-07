package app.service.shoppingCart;

import app.model.product.Product;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartItem;
import app.repository.product.ProductRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ShoppingCartService {
    private static final Logger logger = Logger.getLogger(ShoppingCartService.class);

    private final ShoppingCartRepository cartRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    @Transactional
    public void setCartItem(Long userId, Long productId, int count) {
        ShoppingCart cart = cartRepository.findByUserId(userId);
        if (cart == null) return;

        for (ShoppingCartItem cartItem : cart.getCartItems()) {
            if(Objects.equals(cartItem.getProduct().getId(), productId)){
                cartItem.setCount(count);
                return;
            }
        }

        Product product = productRepository.findById(productId).orElse(null);
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setShoppingCart(cart);
        cartItem.setProduct(product);
        cartItem.setCount(count);
        cart.setCartItem(cartItem);
    }

    @Transactional
    public void removeCartItem(Long userId, Long productId) {
        ShoppingCart cart = cartRepository.findByUserId(userId);
        if (cart == null) return;
        cart.getCartItems().removeIf(cartItem -> Objects.equals(cartItem.getProduct().getId(), productId));
    }

    @Transactional
    public void refreshShoppingCart(Long userId) {
        ShoppingCart cart = cartRepository.findByUserId(userId);
        if (cart == null) return;
        cart.getCartItems().clear();
    }
}
