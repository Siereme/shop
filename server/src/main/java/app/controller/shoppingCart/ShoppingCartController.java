package app.controller.shoppingCart;

import app.exception.EntityNotFoundException;
import app.model.order.Order;
import app.model.shoppingCart.ShoppingCart;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.repository.user.UserRepository;
import app.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository repository;
    @Autowired
    private ShoppingCartService service;
    @Autowired
    private UserRepository userRepo;

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll(){
        try {
            List<ShoppingCart> carts = repository.findAll();
            return ResponseEntity.ok().body(carts);
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id){
        try{
            ShoppingCart cart = userRepo.findShoppingCartById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
            return ResponseEntity.ok().body(cart);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addToCart(Long userId, Long productId, int count){
        try{
            service.setCartItem(userId, productId, count);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<?> removeCartItem(Long userId, Long productId){
        try{
            service.removeCartItem(userId, productId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
