package com.shop.productcatalogserver.controller.main;

import com.shop.productcatalogserver.dto.category.CategoryConfigDTO;
import com.shop.productcatalogserver.dto.category.CategoryResponse;
import com.shop.productcatalogserver.dto.main.MainConfigDTO;
import com.shop.productcatalogserver.dto.main.MainResponse;
import com.shop.productcatalogserver.service.category.CategoryService;
import com.shop.productcatalogserver.service.main.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/main")
public class MainController {

    @Autowired
    private MainService mainService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/main-page")
    public ResponseEntity<?> getMainByConfig(@RequestBody MainConfigDTO config) {
        try {
            MainResponse response = mainService.getByConfig(config);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/category-page")
    public ResponseEntity<?> findCategoryByConfig(@RequestBody CategoryConfigDTO categoryDTO) {
        try {
            CategoryResponse category = categoryService.getByConfig(categoryDTO);
            return ResponseEntity.ok().body(category);
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
