package app.service.product;

import app.model.category.Category;
import app.model.dto.product.IProductDTO;
import app.model.dto.product.ProductDTO;
import app.model.product.Product;
import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;
import app.model.shoppingCart.ShoppingCartProductItem;
import app.repository.category.CategoryRepository;
import app.repository.product.ProductOptionRepository;
import app.repository.product.ProductRepository;
import app.repository.shoppingCart.ShoppingCartProductItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService<Product> {

    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ProductOptionRepository optionRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ShoppingCartProductItemRepository cartItemRepo;

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setArticle(productDTO.getArticle());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageLink(productDTO.getImageLink());

        //add description
        ProductDescription description = new ProductDescription();
        if (productDTO.getDescription() != null) {
            description.setShortDescription(productDTO.getDescription().getShortDescription());
            description.setLongDescription(productDTO.getDescription().getLongDescription());
        }
        product.setDescription(description);

        //add options
        Set<ProductOption> options = productDTO.getOptions()
                .stream()
                .map(option -> {
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


    public void deleteById(Long id) {
        Product product = productRepo.findByIdWithCategoryProducts(id)
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));

        int orderProductsCount = productRepo.findCountOrderItems(id).orElse(0);
        if (orderProductsCount > 0) {
            throw new IllegalArgumentException("The product is contained in the completed orders");
        }

        List<ShoppingCartProductItem> productItems = productRepo.findCartItems(id).orElseGet(Collections::emptyList);
        if(!productItems.isEmpty()){
            productItems.forEach(cartItemRepo::delete);
        }

        productRepo.delete(product);
    }

    public List<Product> getPopular() {
        List<Long> productIds = productRepo.findPopularIds(PageRequest.of(0, 10))
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));
        return productRepo.findAllById(productIds);
    }


    public List<Product> findByCategoryId(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found"));
        return productRepo.findByPath(category.getPath())
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));
    }

    public List<Product> findByCategoryPath(String path, int depth) {
        String targetPath = path.substring(0, StringUtils.ordinalIndexOf(path, "/", depth) + 1);
        return productRepo.findByPath(targetPath)
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));
    }

    public List<IProductDTO> findByCategoryPathWithIds(String path, int depth) {
        String targetPath = path.substring(0, StringUtils.ordinalIndexOf(path, "/", depth) + 1);
        return productRepo.findByPathWithIds(targetPath)
                .orElseThrow(() -> new EntityNotFoundException("Products is not found"));
    }
}
