package app.controller.user;

import app.model.user.User;
import app.repository.user.UserRepository;
import app.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserRepository userRepo;
    private final UserService userService;

    public UserController(UserRepository userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
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

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User userDTO){
        User user = userService.addUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/add/anonymous")
    public ResponseEntity<User> addAnonymousUser(){
        User user = userService.addAnonymousUser();
        return ResponseEntity.ok().body(user);
    }
}
