package app.service.shoppingCart;

import app.model.shoppingCart.ShoppingCartProductItem;

public interface IShoppingCartService {

    ShoppingCartProductItem setCartItem(Long userId, Long productId, int count);

    void removeCartItem(Long userId, Long productId);

}
