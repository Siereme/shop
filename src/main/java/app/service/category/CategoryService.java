package app.service.category;

import app.model.category.Category;
import app.model.dto.category.CategoryDTO;
import app.repository.category.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepo;


    public List<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category findById(Long id){
        return categoryRepo.findById(id).orElse(null);
    }

    public Set<Category> findByIds(List<Long> ids){
        return new HashSet<>(categoryRepo.findAllById(ids));
    }

    public Set<Category> findAllFirstLevel(){
        return categoryRepo.findAllFirstLevel();
    }

    public Set<Category> findByDepth(int depth) {
        return categoryRepo.findByDepth(depth);
    }

    public Set<Category> findByLineageAndDepth(Long lineage, int depth) {
        return categoryRepo.findByLineageAndDepth(lineage, depth);
    }

    public void addCategory(CategoryDTO categoryDTO){
        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setImageLink(categoryDTO.getImageLink());

        //add parent category
        Category parentCategory = categoryRepo.findById(categoryDTO.getParentId()).orElse(null);
        category.setParent(parentCategory);

        //save category
        category = categoryRepo.saveAndFlush(category);

        logger.info("Added new product " + category.getName());
    }

    public void deleteCategory(Long categoryId){
        categoryRepo.deleteById(categoryId);
    }
}
