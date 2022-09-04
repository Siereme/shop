package app.service.shoppingCart;

import app.model.shoppingCart.ShoppingCart;

public interface IShoppingCartService {

    ShoppingCart setCartItem(Long userId, Long productId, int count);

    ShoppingCart removeCartItem(Long userId, Long productId);

}
