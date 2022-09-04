package app.controller.order;

import app.model.dto.order.OrderDTO;
import app.model.dto.order.OrderResponse;
import app.model.order.Order;
import app.repository.order.OrderRepository;
import app.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderService orderService;


    @GetMapping(value = "/all")
    public ResponseEntity<?> getOrders() {
        try {
            List<Order> orders = orderRepo.findAll();
            return ResponseEntity.ok().body(orders);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long id) {
        try {
            List<Order> orders = orderRepo.findAllByUserId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Orders is not found"));
            return ResponseEntity.ok().body(orders);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderService.createOrder(orderDTO);
            return ResponseEntity.ok().body(new OrderResponse(order));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
