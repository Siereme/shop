package app.controller.user;

import app.model.order.Order;
import app.model.user.User;
import app.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepo.findAll();

        if(users.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userRepo.findById(id).orElse(null);
        return ResponseEntity.ok().body(user);
    }
}
