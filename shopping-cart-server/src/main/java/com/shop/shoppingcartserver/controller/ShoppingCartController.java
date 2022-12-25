package com.shop.shoppingcartserver.controller;

import com.shop.shoppingcartserver.model.ShoppingCart;
import com.shop.shoppingcartserver.repository.ShoppingCartRepository;
import com.shop.shoppingcartserver.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private ShoppingCartService cartService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll() {
        try {
            List<ShoppingCart> carts = cartRepo.findAll();
            return ResponseEntity.ok().body(carts);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id) {
        try {
            ShoppingCart cart = cartRepo.findByCustomerId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
            return ResponseEntity.ok().body(cart);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/user/add/{id}")
    public ResponseEntity<?> addCart(@PathVariable Long id) {
        try {
            ShoppingCart shoppingCart = cartService.setCart(id);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addToCart(Long customerId, Long sku, int count) {
        try {
            ShoppingCart cartItem = cartService.setCartItem(customerId, sku, count);
            return ResponseEntity.ok().body(cartItem);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> removeCartItem(Long customerId, Long sku) {
        try {
            ShoppingCart shoppingCart = cartService.removeCartItem(customerId, sku);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/clear/id/{id}")
    public ResponseEntity<?> clearByUserId(@PathVariable Long id) {
        try {
            cartService.clearByUserId(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
