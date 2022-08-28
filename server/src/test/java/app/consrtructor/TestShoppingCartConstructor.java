package app.consrtructor;

import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
import org.assertj.core.util.Sets;

import java.util.*;

public class TestShoppingCartConstructor {

    private final TestProductConstructor productConstructor = new TestProductConstructor();
    private final List<ShoppingCart> shoppingCartList = new ArrayList<>();

    public TestShoppingCartConstructor() {
        construct();
    }

    private void construct() {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);

        ShoppingCartProductItem productItem11 = new ShoppingCartProductItem();
        productItem11.setId(1L);
        productItem11.setShoppingCart(shoppingCart1);
        productItem11.setProduct(productConstructor.getById(1L));
        productItem11.setCount(7);

        ShoppingCartProductItem productItem12 = new ShoppingCartProductItem();
        productItem12.setId(2L);
        productItem12.setShoppingCart(shoppingCart1);
        productItem12.setProduct(productConstructor.getById(2L));
        productItem12.setCount(11);

        shoppingCart1.setCartItems(Sets.newHashSet(Arrays.asList(productItem11, productItem12)));

        shoppingCart1.setTotal(194500d);
        shoppingCart1.setCount(2);


        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(2L);

        ShoppingCartProductItem productItem21 = new ShoppingCartProductItem();
        productItem21.setId(3L);
        productItem21.setShoppingCart(shoppingCart2);
        productItem21.setProduct(productConstructor.getById(3L));
        productItem21.setCount(9);

        ShoppingCartProductItem productItem22 = new ShoppingCartProductItem();
        productItem22.setId(4L);
        productItem22.setShoppingCart(shoppingCart2);
        productItem22.setProduct(productConstructor.getById(4L));
        productItem22.setCount(10);

        shoppingCart2.setCartItems(Sets.newHashSet(Arrays.asList(productItem21, productItem22)));

        shoppingCart2.setTotal(214500d);
        shoppingCart2.setCount(2);

        shoppingCartList.addAll(List.of(shoppingCart1, shoppingCart2));
    }

    public List<ShoppingCart> getAll() {
        return shoppingCartList;
    }

    public ShoppingCart getById(Long id) {
        return shoppingCartList
                .stream()
                .filter(shoppingCart -> Objects.equals(shoppingCart.getId(), id))
                .findFirst().orElseGet(ShoppingCart::new);
    }
}
