package app.controller.order;

import app.model.dto.order.OrderDTO;
import app.model.dto.order.OrderResponseDTO;
import app.model.dto.user.AuthenticationUserDTO;
import app.model.order.Order;
import app.utils.constants.user.UserStatus;
import app.repository.order.OrderRepository;
import app.service.authentication.AuthenticationService;
import app.service.order.OrderService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = orderRepo.findAll();

        if(orders.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/user-id/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long id){
        List<Order> orders = orderRepo.findAllByUserId(id);

        if(orders.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request, HttpServletResponse response){
        try {
            Order order = orderService.createOrder(orderDTO);
            if(order == null) throw new Exception("Create order error");

            if(Objects.equals(order.getUser().getStatus(), UserStatus.ANONYMOUS)){
                authenticationService.logout(request, response);
                AuthenticationUserDTO userDTO = authenticationService.anonymousAuthenticate(order.getUser());
                return ResponseEntity.ok().body(new OrderResponseDTO(userDTO.getToken(), userDTO.getUser(), order));
            }
            return ResponseEntity.ok().body(new OrderResponseDTO(order));

        } catch (Exception ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteOrder(Long orderId){
        orderRepo.deleteById(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/delete-all")
    public ResponseEntity<?> deleteAllOrder(){
        orderRepo.deleteAll();
        return ResponseEntity.ok().build();
    }
}
