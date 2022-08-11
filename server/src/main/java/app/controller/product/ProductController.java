package app.controller.product;

import app.exception.EntityNotFoundException;
import app.model.dto.product.ProductDTO;
import app.model.product.Product;
import app.repository.product.ProductRepository;
import app.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(productRepo.findAll());
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            Product product = productRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product is not found"));
            return ResponseEntity.ok().body(product);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/ids/{ids}")
    public ResponseEntity<?> findByIds(@PathVariable List<Long> ids){
        try{
            List<Product> products = productRepo.findAllById(ids);
            return ResponseEntity.ok().body(products);
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/category_id/{id}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long id){
        try{
            List<Product> products = service.findByCategoryId(id);
            return ResponseEntity.ok().body(products);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/popular")
    public ResponseEntity<?> findPopularProducts(){
        try{
            List<Product> products = service.getPopular();
            return ResponseEntity.ok().body(products);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        try{
            Product product = service.addProduct(productDTO);
            return ResponseEntity.ok().body(product);
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            service.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
