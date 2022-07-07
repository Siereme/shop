package app.controller.order;

import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.repository.order.OrderRepository;
import app.service.order.OrderService;
import app.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderRepository repository;
    private final OrderService service;
    private final UserService userService;

    public OrderController(OrderRepository repository, OrderService service, UserService userService) {
        this.repository = repository;
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = repository.findAll();

        if(orders.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO order){
        try {
            service.createOrder(order);
            return ResponseEntity.ok().build();
        } catch (Exception ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteOrder(Long orderId){
        service.removeOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/delete-all")
    public ResponseEntity<?> deleteAllOrder(){
        service.removeAll();
        return ResponseEntity.ok().build();
    }
}
