package com.shop.customerserver.controller;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.dto.UserDetailsDTO;
import com.shop.customerserver.exception.CustomerAlreadyExistsException;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.repository.CustomerRepository;
import com.shop.customerserver.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepo;
    private final CustomerService customerService;
    private final WebClient.Builder webClientBuilder;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getCustomers() {
        try {
            List<Customer> customers = customerRepo.findAll();
            return ResponseEntity.ok().body(customers);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer is not found"));
            return ResponseEntity.ok().body(customer);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/info")
    public ResponseEntity<?> getUserDetailsById(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        try {
            Customer customer = customerService.info(jwt, principal);
            return ResponseEntity.ok().body(new CustomerDTO(customer));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add/anonymous")
    public ResponseEntity<?> addAnonymousCustomer() {
        try {
            Customer customer = customerService.createAnonymousCustomer();
            return ResponseEntity.ok().body(new UserDetailsDTO(customer));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerService.createCustomer(customerDTO);
            return ResponseEntity.ok().body(new UserDetailsDTO(customer));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerService.updateCustomer(customerDTO);
            return ResponseEntity.ok().body(new UserDetailsDTO(customer));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/exist/{id}")
    public ResponseEntity<?> isExist(@PathVariable Long id) {
        try {
            boolean isExist = customerService.isExist(id);
            return ResponseEntity.ok().body(isExist);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public Map<String, String> handleUserAlreadyExistsExceptions(CustomerAlreadyExistsException ex) {
        return ex.getMessages();
    }

}
