package app.controller.user;

import app.model.user.IUser;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/all")
    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userRepo.findAll();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Orders is not found"));
            return ResponseEntity.ok().body(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addUser(@RequestBody User userDTO) {
        try {
            IUser user = userService.createUser(userDTO);
            return ResponseEntity.ok().body(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add/anonymous")
    public ResponseEntity<?> addAnonymousUser() {
        try {
            User user = userService.createAnonymousUser();
            return ResponseEntity.ok().body(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
