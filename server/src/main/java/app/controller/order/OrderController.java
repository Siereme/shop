package app.controller.order;

import app.exception.EntityNotFoundException;
import app.model.dto.order.OrderDTO;
import app.model.dto.order.OrderResponseDTO;
import app.model.dto.user.AuthenticationUserResponse;
import app.model.order.Order;
import app.utils.constants.user.UserStatus;
import app.repository.order.OrderRepository;
import app.service.authentication.AuthenticationService;
import app.service.order.OrderService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping(value = "/all")
    public ResponseEntity<?> getOrders(){
        try{
            List<Order> orders = orderRepo.findAll();
            return ResponseEntity.ok().body(orders);
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long id){
        try{
            List<Order> orders = orderRepo.findAllByUserId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Orders is not found"));
            return ResponseEntity.ok().body(orders);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request, HttpServletResponse response){
        try {
            Order order = orderService.createOrder(orderDTO);
            return ResponseEntity.ok().body(new OrderResponseDTO(order));
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteOrder(Long orderId){
        try {
            orderRepo.deleteById(orderId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete-all")
    public ResponseEntity<?> deleteAllOrder(){
        try {
            orderRepo.deleteAll();
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
