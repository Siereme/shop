package com.shop.productcatalogserver.controller.search;

import com.shop.productcatalogserver.dto.search.SearchCategoryDTO;
import com.shop.productcatalogserver.dto.search.SearchCategoryResponse;
import com.shop.productcatalogserver.dto.search.SearchDTO;
import com.shop.productcatalogserver.dto.search.SearchResponse;
import com.shop.productcatalogserver.service.search.SearchCategoryService;
import com.shop.productcatalogserver.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/search")
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
