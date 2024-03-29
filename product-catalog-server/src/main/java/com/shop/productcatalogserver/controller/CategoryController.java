package com.shop.productcatalogserver.controller;

import com.shop.productcatalogserver.dto.category.CategoryPathDTO;
import com.shop.productcatalogserver.dto.category.CategoryRequest;
import com.shop.productcatalogserver.model.category.Category;
import com.shop.productcatalogserver.repository.category.CategoryRepository;
import com.shop.productcatalogserver.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll() {
        try {
            List<Category> categories = categoryRepo.findAll();
            return ResponseEntity.ok().body(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Category category = categoryRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
            return ResponseEntity.ok().body(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/ids/{ids}")
    public ResponseEntity<?> findByIds(@PathVariable List<Long> ids) {
        try {
            List<Category> categories = categoryRepo.findAllById(ids);
            return ResponseEntity.ok().body(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/first-level")
    public ResponseEntity<?> findAllFirstLevel() {
        try {
            List<Category> categories = categoryRepo.findByDepth(1)
                    .orElseThrow(() -> new EntityNotFoundException("Categories is not found"));
            return ResponseEntity.ok().body(categories);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/depth/{depth}")
    public ResponseEntity<?> findByDepth(@PathVariable int depth) {
        try {
            List<Category> categories = categoryRepo.findByDepth(depth)
                    .orElseThrow(() -> new EntityNotFoundException("Categories is not found"));
            return ResponseEntity.ok().body(categories);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/path")
    public ResponseEntity<?> findByPathAndDepth(@RequestBody CategoryPathDTO categoryDTO) {
        try {
            Category category =
                    categoryService.getByPathAndDepth(categoryDTO.getPath(), categoryDTO.getDepth());
            return ResponseEntity.ok().body(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addProduct(@RequestBody CategoryRequest categoryRequest) {
        try {
            Category category = categoryService.addCategory(categoryRequest);
            return ResponseEntity.ok().body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
