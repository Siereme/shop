package app.model.dto.main;

import app.model.category.Category;
import app.model.order.Order;
import app.model.product.Product;
import app.model.shoppingCart.ShoppingCart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MainResponse {
    private List<Category> categories;
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    private List<Product> productsPopular;
}
