package app.service.shoppingCart;

import app.exception.EntityNotFoundException;
import app.model.product.Product;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import app.repository.product.ProductRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
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


    @Transactional
    public ShoppingCartProductItem setCartItem(Long userId, Long productId, int count) {
        ShoppingCart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));


        ShoppingCartProductItem cartProductItem = cart.getCartItems().stream()
                .filter(item -> Objects.equals(item.getProduct().getId(), productId))
                .findFirst().orElse(null);

        if (cartProductItem != null) {
            cartProductItem.setCount(count);
            return cartProductItem;
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
        return cartItem;
    }

    private void calculateTotal(ShoppingCart cart) {
        double total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice() * item.getCount())
                .reduce(0d, Double::sum);
        cart.setTotal(total);
    }

    @Transactional
    public void removeCartItem(Long userId, Long productId) {
        ShoppingCart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
        cart.getCartItems().removeIf(cartItem -> Objects.equals(cartItem.getProduct().getId(), productId));
    }

}
