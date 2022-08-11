package app.controller.shoppingCart;

import app.model.shoppingCart.ShoppingCart;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shopping-cart")
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
            ShoppingCart cart = cartRepo.findByUserId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
            return ResponseEntity.ok().body(cart);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addToCart(Long userId, Long productId, int count) {
        try {
            cartService.setCartItem(userId, productId, count);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<?> removeCartItem(Long userId, Long productId) {
        try {
            cartService.removeCartItem(userId, productId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
