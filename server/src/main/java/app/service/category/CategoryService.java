package app.service.category;

import app.model.category.Category;
import app.model.dto.category.CategoryConfigDTO;
import app.model.dto.category.CategoryRequest;
import app.model.dto.category.CategoryResponse;
import app.model.dto.category.ICategoryDTO;
import app.model.dto.product.IProductDTO;
import app.repository.category.CategoryRepository;
import app.service.product.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService implements ICategoryService<Category> {

    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductService productService;

    public Category addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();

        category.setName(categoryRequest.getName());
        category.setImageLink(categoryRequest.getImageLink());

        //add parent category
        Category parentCategory = categoryRepo.findById(categoryRequest.getParentId())
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

    public ICategoryDTO findByIdWithoutSubcategories(Long id){
        return categoryRepo.findByIdWithoutSubcategories(id)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
    }

    public ICategoryDTO findByPathWithoutSubcategories(String path){
        return categoryRepo.findByPathWithoutSubcategories(path)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
    }

    public Category getByPathAndDepth(String path, int depth) {
        String targetPath = path.substring(0, StringUtils.ordinalIndexOf(path, "/", depth) + 1);
        return categoryRepo.findByPath(targetPath)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
    }

    public CategoryResponse getByConfig(CategoryConfigDTO config) {
        CategoryResponse responseDTO = new CategoryResponse();

        ICategoryDTO category = findByIdWithoutSubcategories(config.getCategoryId());
        responseDTO.setCategory(category);

        if(config.isWithParent()){
            Category parentCategory = getByPathAndDepth(category.getPath(), 1);
            responseDTO.setParentCategory(parentCategory);
        }

        if(config.isWithProducts()){
            Pageable page = PageRequest.of(config.getPage(), config.getPageSize());
            Page<IProductDTO> products = productService.findByPathWithCategoryIds(category.getPath(), page);
            responseDTO.setProducts(products.getContent());
            responseDTO.setPageCount(products.getTotalPages());
        }

        return responseDTO;
    }
}