package com.shop.shoppingcartserver.repository;

import com.shop.shoppingcartserver.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByCustomerId(Long id);

    void deleteByCustomerId(Long id);
}
