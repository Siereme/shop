package app.controller.order;

import app.model.dto.order.OrderDTO;
import app.model.dto.order.OrderResponseDTO;
import app.model.dto.user.AuthenticationUserDTO;
import app.model.order.Order;
import app.repository.order.OrderRepository;
import app.service.authentication.AuthenticationService;
import app.service.order.OrderService;
import app.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderRepository repository;
    private final OrderService service;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public OrderController(OrderRepository repository, OrderService service, UserService userService, AuthenticationService authenticationService) {
        this.repository = repository;
        this.service = service;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = repository.findAll();

        if(orders.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long id){
        List<Order> orders = repository.findAllByUserId(id);

        if(orders.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request, HttpServletResponse response){
        try {
            Order order = service.createOrder(orderDTO);
            if(order == null) throw new Exception("Create order error");

            authenticationService.logout(request, response);
            String email = order.getUser().getEmail();
            String password = order.getUser().getPhone();
            AuthenticationUserDTO userDTO = authenticationService.authenticate(email, password);

            OrderResponseDTO orderResponseDTO = new OrderResponseDTO(userDTO.getToken(), userDTO.getUser(), order);
            return ResponseEntity.ok().body(orderResponseDTO);
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
