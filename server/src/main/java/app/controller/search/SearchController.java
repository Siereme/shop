package app.controller.search;

import app.model.dto.category.CategoryConfigDTO;
import app.model.dto.search.SearchCategoryDTO;
import app.model.dto.search.SearchCategoryResponse;
import app.model.dto.search.SearchDTO;
import app.model.dto.search.SearchResponse;
import app.model.product.Product;
import app.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/search")
public class SearchController {

    @Autowired
    SearchService searchService;


    @GetMapping(value = "/")
    public ResponseEntity<SearchResponse> findAll(@RequestParam String query, @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok().body(searchService.search(query, page));
    }

    @PostMapping(value = "/options")
    public ResponseEntity<SearchResponse> findByOptions(@RequestBody SearchDTO config) {
        return ResponseEntity.ok().body(searchService.searchByOptions(config));
    }

    @PostMapping(value = "/category")
    public ResponseEntity<SearchCategoryResponse> findCategory(@RequestBody CategoryConfigDTO config) {
        return ResponseEntity.ok().body(searchService.searchCategory(config));
    }

    @PostMapping(value = "/category/options")
    public ResponseEntity<SearchResponse> findByOptions(@RequestBody SearchCategoryDTO config) {
        return ResponseEntity.ok().body(searchService.searchCategoryByOptions(config));
    }
}
