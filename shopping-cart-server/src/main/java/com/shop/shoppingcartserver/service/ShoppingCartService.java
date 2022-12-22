package com.shop.shoppingcartserver.service;

import com.shop.shoppingcartserver.model.ShoppingCart;
import com.shop.shoppingcartserver.model.ShoppingCartLineItem;
import com.shop.shoppingcartserver.model.ShoppingCartLineItems;
import com.shop.shoppingcartserver.repository.ShoppingCartRepository;
import com.shop.shoppingcartserver.utils.constant.ServiceUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.HttpMethod;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartService implements IShoppingCartService {

    private final ShoppingCartRepository cartRepo;
    private final RestTemplate restTemplate;


    public ShoppingCart setCartItem(Long customerId, Long article, int quantity) {
        ShoppingCart cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));


        ShoppingCartLineItem cartProductItem = cart.getCartLineItems().getLineItems().stream()
                .filter(item -> Objects.equals(item.getArticle(), article))
                .findFirst().orElse(null);

        if (cartProductItem != null) {
            cartProductItem.setQuantity(quantity);
            cart.setTotal(calculateTotal(cart.getCartLineItems().getLineItems()));
            return cart;
        }

        ShoppingCartLineItem cartLineItem = restTemplate.getForEntity(
                ServiceUrl.PRODUCT_BY_ARTICLE +  article,
                ShoppingCartLineItem.class
        ).getBody();
        Optional.ofNullable(cartLineItem)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));
        cartLineItem.setQuantity(quantity);

        cart.getCartLineItems().getLineItems().add(cartLineItem);
        cart.setCount(cart.getCartLineItems().getLineItems().size());
        cart.setTotal(calculateTotal(cart.getCartLineItems().getLineItems()));
        return cart;
    }

    public ShoppingCart removeCartItem(Long customerId, Long article) {
        ShoppingCart cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
        cart.getCartLineItems().getLineItems().removeIf(cartItem -> Objects.equals(cartItem.getArticle(), article));
        cart.setTotal(calculateTotal(cart.getCartLineItems().getLineItems()));
        cart.setCount(cart.getCartLineItems().getLineItems().size());
        return cart;
    }

    public Double calculateTotal(Collection<ShoppingCartLineItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public void clearByUserId(Long customerId) {
        ShoppingCart shoppingCart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));

        shoppingCart.clear();
    }

    public ShoppingCart setCart(Long customerId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerId(customerId);
        shoppingCart.setCartLineItems(new ShoppingCartLineItems());
        return cartRepo.save(shoppingCart);
    }
}
