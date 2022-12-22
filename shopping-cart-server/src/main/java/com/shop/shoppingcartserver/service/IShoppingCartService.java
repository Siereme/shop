package com.shop.shoppingcartserver.service;

import com.shop.shoppingcartserver.model.ShoppingCart;

public interface IShoppingCartService {

    ShoppingCart setCartItem(Long userId, Long article, int count);

    ShoppingCart removeCartItem(Long userId, Long article);

}
