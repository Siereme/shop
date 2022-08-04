package app.service.category;

import app.model.category.Category;
import app.model.dto.category.CategoryDTO;
import app.repository.category.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService implements ICategoryService<Category> {
    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepo;

    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setImageLink(categoryDTO.getImageLink());

        //add parent category
        Category parentCategory = categoryRepo.findById(categoryDTO.getParentId()).orElse(null);
        category.setParent(parentCategory);

        logger.info("Added new product " + category.getName());

        //save category
        return categoryRepo.saveAndFlush(category);
    }

}
