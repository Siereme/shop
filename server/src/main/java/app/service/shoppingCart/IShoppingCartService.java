package app.service.shoppingCart;

import app.model.shoppingCart.ShoppingCart;
import app.model.user.User;

public interface IShoppingCartService {

    ShoppingCart addShoppingCart(User user);

    void setCartItem(Long userId, Long productId, int count);

    void removeCartItem(Long userId, Long productId);

    void refreshShoppingCart(Long userId);

}
