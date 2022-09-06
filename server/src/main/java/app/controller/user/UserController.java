package app.controller.user;

import app.model.dto.user.AuthenticationUserResponse;
import app.model.user.IUser;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.service.authentication.AuthenticationService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authService;


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
    public ResponseEntity<?> addUser(@Valid @RequestBody User userDTO) {
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

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User userDTO) {
        try {
            User user = userService.updateUser(userDTO);
            AuthenticationUserResponse response = authService.createToken(user);
            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
