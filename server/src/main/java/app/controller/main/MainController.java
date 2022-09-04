package app.controller.main;

import app.model.dto.category.CategoryConfigDTO;
import app.model.dto.category.CategoryResponse;
import app.model.dto.main.MainConfigDTO;
import app.model.dto.main.MainResponse;
import app.service.category.CategoryService;
import app.service.main.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "api/v1/main")
public class MainController {

    @Autowired
    private MainService mainService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/main-page")
    public ResponseEntity<?> getByConfig(@RequestBody MainConfigDTO config){
        try {
            MainResponse response = mainService.getByConfig(config);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/category-page")
    public ResponseEntity<?> findByConfig(@RequestBody CategoryConfigDTO categoryDTO) {
        try {
            CategoryResponse category = categoryService.getByConfig(categoryDTO);
            return ResponseEntity.ok().body(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
