package com.shop.shoppingcartserver.service;

import com.shop.shoppingcartserver.model.ShoppingCart;
import com.shop.shoppingcartserver.model.ShoppingCartLineItem;
import com.shop.shoppingcartserver.model.ShoppingCartLineItems;
import com.shop.shoppingcartserver.repository.ShoppingCartRepository;
import com.shop.shoppingcartserver.utils.constant.ServiceUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartService implements IShoppingCartService {

    private final ShoppingCartRepository cartRepo;
    private final WebClient.Builder webClientBuilder;


    public ShoppingCart setCartItem(Long customerId, Long sku, int quantity) {
        ShoppingCart cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));

        List<ShoppingCartLineItem> lineItems = cart.getCartLineItems().getLineItems();

        ShoppingCartLineItem cartProductItem = lineItems.stream()
                .filter(item -> Objects.equals(item.getSku(), sku))
                .findFirst().orElse(null);

        if (cartProductItem != null) {
            cartProductItem.setQuantity(quantity);
            cart.setTotal(calculateTotal(lineItems));
            return cart;
        }

        ShoppingCartLineItem cartLineItem = webClientBuilder.build()
                .get().uri(ServiceUrl.PRODUCT_SKU + sku)
                .retrieve()
                .bodyToMono(ShoppingCartLineItem.class)
                .block();
        Optional.ofNullable(cartLineItem)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));
        cartLineItem.setQuantity(quantity);

        lineItems.add(cartLineItem);
        cart.setCount(lineItems.size());
        cart.setTotal(calculateTotal(lineItems));
        return cart;
    }

    public ShoppingCart removeCartItem(Long customerId, Long sku) {
        ShoppingCart cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
        List<ShoppingCartLineItem> lineItems = cart.getCartLineItems().getLineItems();
        lineItems.removeIf(cartItem -> Objects.equals(cartItem.getSku(), sku));
        cart.setTotal(calculateTotal(lineItems));
        cart.setCount(lineItems.size());
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
