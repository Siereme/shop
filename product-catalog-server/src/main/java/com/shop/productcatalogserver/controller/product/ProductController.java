package com.shop.productcatalogserver.controller.product;

import com.shop.productcatalogserver.dto.category.CategoryPathDTO;
import com.shop.productcatalogserver.dto.product.ILineItemDTO;
import com.shop.productcatalogserver.dto.product.ProductDTO;
import com.shop.productcatalogserver.model.product.Product;
import com.shop.productcatalogserver.repository.product.ProductRepository;
import com.shop.productcatalogserver.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(productRepo.findAll());
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Product product = productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product is not found"));
            return ResponseEntity.ok().body(product);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/ids/{ids}")
    public ResponseEntity<?> findByIds(@PathVariable List<Long> ids) {
        try {
            List<Product> products = productRepo.findAllById(ids);
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/article/{article}")
    public ResponseEntity<?> findByArticle(@PathVariable Long article) {
        try {
            ILineItemDTO product = productRepo.findByArticle(article);
            return ResponseEntity.ok().body(product);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/category-id/{id}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long id) {
        try {
            List<Product> products = productService.findByCategoryId(id);
            return ResponseEntity.ok().body(products);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/by-category/path-depth")
    public ResponseEntity<?> findByPathAndDepth(@RequestBody CategoryPathDTO categoryDTO) {
        try {
            List<Product> products = productService.findByCategoryPath(categoryDTO.getPath(), categoryDTO.getDepth());
            return ResponseEntity.ok().body(products);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/popular")
    public ResponseEntity<?> findPopularProducts() {
        try {
            List<Product> products = productService.getPopular();
            return ResponseEntity.ok().body(products);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.addProduct(productDTO);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
