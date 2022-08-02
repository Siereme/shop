package app.controller.user;

import app.model.user.IUser;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;


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
    public ResponseEntity<IUser> addUser(@RequestBody User userDTO){
        IUser user = userService.createUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/add/anonymous")
    public ResponseEntity<User> addAnonymousUser(){
        User user = userService.createAnonymousUser();
        return ResponseEntity.ok().body(user);
    }
}
