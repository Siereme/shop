package app.repository.shoppingCart;

import app.model.shoppingCart.ShoppingCartProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductItemRepository extends JpaRepository<ShoppingCartProductItem, Long> {
}
