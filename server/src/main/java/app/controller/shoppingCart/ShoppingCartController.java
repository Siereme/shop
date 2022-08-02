package app.controller.shoppingCart;

import app.exception.EntityNotFoundException;
import app.model.shoppingCart.ShoppingCart;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.service.shoppingCart.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartRepository repository;
    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartRepository repository, ShoppingCartService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ShoppingCart>> findAll(){
        List<ShoppingCart> carts = repository.findAll();

        if(carts.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(carts);
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<ShoppingCart> findByUserId(@PathVariable Long id){
        ShoppingCart cart = repository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
        return ResponseEntity.ok().body(cart);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addToCart(Long userId, Long productId, int count){
        service.setCartItem(userId, productId, count);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<?> removeCartItem(Long userId, Long productId){
        service.removeCartItem(userId, productId);
        return ResponseEntity.ok().build();
    }
}
