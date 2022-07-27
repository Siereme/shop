package app.service.product;

import app.model.category.Category;
import app.model.dto.filter.Filter;
import app.model.product.Product;
import app.model.product.ProductDescription;
import app.model.product.ProductOption;
import app.repository.category.CategoryRepository;
import app.repository.product.ProductDescriptionRepository;
import app.repository.product.ProductOptionRepository;
import app.repository.product.ProductRepository;
import app.utils.specification.ProductSpecificationBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductDescriptionRepository productDescriptionRepo;
    @Autowired
    ProductOptionRepository productOptionRepo;
    @Autowired
    CategoryRepository categoryRepo;

    public void addProduct(Product productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        //add description
        ProductDescription description = new ProductDescription();
        description.setDescription(productDTO.getDescription().getDescription());
        description = productDescriptionRepo.save(description);
        product.setDescription(description);

        //add options
        Set<ProductOption> options = new HashSet<>();
        for (ProductOption option : productDTO.getOptions()) {
            ProductOption addOption;
            try {
                addOption = productOptionRepo.findByNameAndValue(option.getName(), option.getValue())
                        .orElseThrow(() -> new IllegalArgumentException("The given id is invalid"));
            }catch (IllegalArgumentException ex){
                addOption = new ProductOption();
                addOption.setName(option.getName());
                addOption.setValue(option.getValue());
                addOption = productOptionRepo.save(addOption);
            }
            options.add(addOption);
        }
        product.setOptions(options);

        //add categories
        List<Long> categoriesIds = productDTO.getCategories().stream().map(Category::getId).collect(Collectors.toList());
        Set<Category> categories = new HashSet<>(categoryRepo.findAllById(categoriesIds));
        product.setCategories(categories);

        //save product
        productRepo.saveAndFlush(product);

        logger.info("Added new product " + product.getName());
    }


    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if(product != null){
            product.getOptions().clear();
            product.getCategories().clear();
            productRepo.deleteById(id);
        }
    }

    public List<Product> getPopular() {
        List<Long> productIds = productRepo.findPopularIds().subList(0, 10);
        return productRepo.findAllById(productIds);
    }

    public List<Product> filterProducts(List<Filter> filters) {
        ProductSpecificationBuilder specificationBuilder = new ProductSpecificationBuilder(filters);
        List<Product> products = productRepo.findAll(specificationBuilder.build());
        return products;
    }

    public List<Product> findByCategoryId(Long id) {
        return productRepo.findByCategoryId(id);
    }
}
