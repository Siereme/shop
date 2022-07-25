package app.controller.category;

import app.model.category.Category;
import app.model.dto.category.CategoryDTO;
import app.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = service.findAll();

        if(categories.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = service.findById(id);

        if(category == null) return ResponseEntity.noContent().build();

        category.setCategories(category.getCategories());
        return ResponseEntity.ok().body(category) ;
    }

    @GetMapping(value = "/ids/{ids}")
    public ResponseEntity<Set<Category>> findByIds(@PathVariable List<Long> ids){
        Set<Category> categories = service.findByIds(ids);

        if(categories.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/first-level")
    public ResponseEntity<Set<Category>> findAllFirstLevel(){
        Set<Category> categories = service.findAllFirstLevel();

        if(categories.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/depth")
    public ResponseEntity<Set<Category>> findByDepth(@RequestParam int depth){
        Set<Category> categories = service.findByDepth(depth);

        if(categories.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/lineage-depth")
    public ResponseEntity<Set<Category>> findByLineageAndDepth(@RequestParam Long lineage, @RequestParam int depth){
        Set<Category> categories = service.findByLineageAndDepth(lineage, depth);

        if(categories.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(categories);
    }


    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody CategoryDTO category){
        service.addCategory(category);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
