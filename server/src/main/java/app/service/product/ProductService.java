package app.service.product;

import app.model.category.Category;
import app.model.dto.product.ProductDTO;
import app.model.product.Product;
import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;
import app.repository.category.CategoryRepository;
import app.repository.product.ProductOptionRepository;
import app.repository.product.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService implements IProductService<Product> {

    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductOptionRepository optionRepo;
    @Autowired
    CategoryRepository categoryRepo;

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setArticle(productDTO.getArticle());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageLink(productDTO.getImageLink());

        //add description
        ProductDescription description = new ProductDescription();
        description.setShortDescription(productDTO.getDescription().getShortDescription());
        description.setLongDescription(productDTO.getDescription().getLongDescription());
        product.setDescription(description);

        //add options
        Set<ProductOption> options = productDTO.getOptions().stream().map(option -> {
            try {
                return optionRepo.findByNameAndValue(option.getName(), option.getValue())
                        .orElseThrow(() -> new EntityNotFoundException("Option is not found"));
            } catch (EntityNotFoundException ex) {
                return new ProductOption(option.getName(), option.getValue());
            }
        }).collect(Collectors.toSet());
        product.setOptions(options);

        //add categories
        product.setCategories(Set.copyOf(categoryRepo.findAllById(productDTO.getCategoriesIds())));

        logger.info("Added new product " + product.getName());

        //save product
        return productRepo.save(product);
    }


    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));
        product.getOptions().clear();
        product.getCategories().clear();
        productRepo.deleteById(id);
    }

    public List<Product> getPopular() {
        List<Long> productIds = productRepo.findPopularIds()
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"))
                .subList(0, 10);
        return productRepo.findAllById(productIds);
    }


    public List<Product> findByCategoryId(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
        return productRepo.findByLineageDepthAndCategoryId(category.getLineage(), category.getDepth(), category.getId())
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));
    }
}
