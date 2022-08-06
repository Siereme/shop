package app.service.shoppingCart;

public interface IShoppingCartService {

    void setCartItem(Long userId, Long productId, int count);

    void removeCartItem(Long userId, Long productId);

    void refreshShoppingCart(Long userId);

}
