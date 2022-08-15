package app.service.shoppingCart;

import app.consrtructor.TestShoppingCartConstructor;
import app.model.shoppingCart.ShoppingCartProductItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartService cartService;
    private final TestShoppingCartConstructor constructor = new TestShoppingCartConstructor();

    @Test
    void setCartItem() {
        ShoppingCartProductItem productItemMock = constructor.getById(1L).getCartItems().iterator().next();

        Mockito.when(cartService.setCartItem(1L, 1L, 7)).thenReturn(productItemMock);

        ShoppingCartProductItem productItem = cartService.setCartItem(1L, 1L, 7);

        Assertions.assertEquals(productItemMock.getProduct().getArticle(), productItem.getProduct().getArticle());
        Assertions.assertEquals(productItemMock.getCount(), productItem.getCount());
    }

    @Test
    void calculateTotal() {
        Set<ShoppingCartProductItem> productItems = constructor.getById(1L).getCartItems();
        double totalMock = 194500d;

        Mockito.when(cartService.calculateTotal(productItems)).thenReturn(totalMock);

        double total = cartService.calculateTotal(productItems);

        Assertions.assertEquals(totalMock, total);
    }

    @Test
    void removeCartItem() {
        cartService.removeCartItem(1L, 1L);

        Mockito.verify(cartService, Mockito.times(1)).removeCartItem(1L, 1L);
    }
}