package app.repository.shoppingCart;

import app.consrtructor.TestShoppingCartConstructor;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository cartRepo;
    private final List<ShoppingCart> shoppingCartList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        shoppingCartList.addAll(new TestShoppingCartConstructor().getAll());
    }

    @Test
    void findByUserId() {
        //Find orders from repository where userId = 1
        ShoppingCart shoppingCart = cartRepo.findByUserId(1L).orElseGet(ShoppingCart::new);

        //Get orders from constructor where userId = 1
        ShoppingCart checkShoppingCart = shoppingCartList.get(0);

        //Comparison of entities
        Assertions.assertEquals(checkShoppingCart.getId(), shoppingCart.getId());
        Assertions.assertEquals(checkShoppingCart.getTotal(), shoppingCart.getTotal());
        Assertions.assertEquals(checkShoppingCart.getCount(), shoppingCart.getCount());
        //// Check shopping cart items
        Assertions.assertEquals(checkShoppingCart.getCartItems().size(), shoppingCart.getCartItems().size());
        Assertions.assertTrue(
                shoppingCart.getCartItems()
                        .stream()
                        .map(ShoppingCartProductItem::getId)
                        .allMatch(itemId -> checkShoppingCart.getCartItems()
                                .stream()
                                .map(ShoppingCartProductItem::getId)
                                .anyMatch(itemId::equals))
        );
    }
}