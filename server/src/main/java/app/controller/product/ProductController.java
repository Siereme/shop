package app.controller.product;

import app.model.dto.filter.Filter;
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
    ProductRepository repository;
    @Autowired
    ProductService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping(value = "/filters")
    public ResponseEntity<List<Product>> filter(@app.utils.httpHandler.Filter List<Filter> filter,
                                                @RequestParam(required = false) String sort){
        return ResponseEntity.ok().body(service.filterProducts(filter));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = repository.findById(id).orElse(null);

        if(product == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(product);
    }

    @GetMapping(value = "/ids/{ids}")
    public ResponseEntity<List<Product>> findByIds(@PathVariable List<Long> ids){
        return ResponseEntity.ok().body(repository.findAllById(ids));
    }

    @GetMapping(value = "/category_id/{id}")
    public ResponseEntity<List<Product>> findByCategoryId(@PathVariable Long id){
        List<Product> products = service.findByCategoryId(id);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/popular")
    public ResponseEntity<List<Product>> findPopularProducts(){
        List<Product> products = service.getPopular();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        service.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
