package app.service.category;

import app.model.category.Category;
import app.model.dto.category.CategoryDTO;
import app.repository.category.CategoryRepository;
import app.service.product.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService implements ICategoryService<Category> {

    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductService productService;

    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setImageLink(categoryDTO.getImageLink());

        //add parent category
        Category parentCategory = categoryRepo.findById(categoryDTO.getParentId())
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
        category.setParent(parentCategory);

        logger.info("Added new product " + category.getName());

        //save category
        return categoryRepo.save(category);
    }

    public void deleteById(Long id) {
        Category category = categoryRepo.findByIdWithProducts(id)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
        categoryRepo.delete(category);
    }
}
