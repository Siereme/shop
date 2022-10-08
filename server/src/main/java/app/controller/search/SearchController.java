package app.controller.search;

import app.model.dto.search.SearchCategoryDTO;
import app.model.dto.search.SearchCategoryResponse;
import app.model.dto.search.SearchDTO;
import app.model.dto.search.SearchResponse;
import app.service.search.SearchCategoryService;
import app.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    SearchCategoryService categoryService;


    @PostMapping(value = "/")
    public ResponseEntity<SearchResponse> findAll(@RequestBody SearchDTO config) {
        return ResponseEntity.ok().body(searchService.search(config));
    }

    @PostMapping(value = "/options")
    public ResponseEntity<SearchResponse> findByOptions(@RequestBody SearchDTO config) {
        return ResponseEntity.ok().body(searchService.searchByOptions(config));
    }

    @PostMapping(value = "/category")
    public ResponseEntity<SearchCategoryResponse> findCategory(@RequestBody SearchCategoryDTO config) {
        return ResponseEntity.ok().body(categoryService.search(config));
    }

    @PostMapping(value = "/category/options")
    public ResponseEntity<SearchCategoryResponse> findByOptions(@RequestBody SearchCategoryDTO config) {
        return ResponseEntity.ok().body(categoryService.searchByOptions(config));
    }
}
