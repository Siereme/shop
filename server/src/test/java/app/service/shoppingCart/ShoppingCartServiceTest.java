package app.service.shoppingCart;

import app.consrtructor.TestProductConstructor;
import app.consrtructor.TestShoppingCartConstructor;
import app.model.product.Product;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import app.repository.product.ProductRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService cartService;
    @Mock
    private ShoppingCartRepository cartRepo;
    @Mock
    private ProductRepository productRepo;

    private final TestShoppingCartConstructor cartConstructor = new TestShoppingCartConstructor();
    private final TestProductConstructor productConstructor = new TestProductConstructor();

    @Test
    void setCartItem() {
        //Prepare objects
        ShoppingCartProductItem productItemMock = new ShoppingCartProductItem();
        productItemMock.setId(5L);
        productItemMock.setProduct(productConstructor.getById(5L));
        productItemMock.setCount(7);

        Optional<ShoppingCart> cartOptional = Optional.of(cartConstructor.getById(1L));
        Optional<Product> productOptional = Optional.of(productConstructor.getById(5L));

        //When stubs are called
        Mockito.when(cartRepo.findByUserId(1L)).thenReturn(cartOptional);
        Mockito.when(productRepo.findById(5L)).thenReturn(productOptional);

        //Call a real service method
        ShoppingCart shoppingCart = cartService.setCartItem(1L, 5L, 7);

        //Verify stub calls
        Mockito.verify(cartRepo, Mockito.times(1)).findByUserId(1L);
        Mockito.verify(productRepo, Mockito.times(1)).findById(5L);

        //Check the resulting object
        Assertions.assertTrue(
                shoppingCart.getCartItems()
                        .stream()
                        .map(item -> item.getProduct().getId())
                        .anyMatch(Long.valueOf(5)::equals)
        );
    }

    @Test
    void calculateTotal() {
        //Prepare objects
        Set<ShoppingCartProductItem> productItems = cartConstructor.getById(1L).getCartItems();
        double totalCheck = 995083d;

        //Call a real service method
        double total = cartService.calculateTotal(productItems);

        //Check the resulting total
        Assertions.assertEquals(totalCheck, total);
    }

    @Test
    void removeCartItem() {
        //Prepare objects
        ShoppingCart cartMock = cartConstructor.getById(1L);
        Optional<ShoppingCart> cartOptional = Optional.of(cartMock);

        //When stubs are called
        Mockito.when(cartRepo.findByUserId(1L)).thenReturn(cartOptional);

        //Call a real service method
        ShoppingCart shoppingCart = cartService.removeCartItem(1L, 1L);

        //Verify stub calls
        Mockito.verify(cartRepo, Mockito.times(1)).findByUserId(1L);

        //Check the resulting object
        Assertions.assertFalse(
                shoppingCart.getCartItems()
                        .stream()
                        .map(item -> item.getProduct().getId())
                        .anyMatch(Long.valueOf(1)::equals)
        );
    }
}