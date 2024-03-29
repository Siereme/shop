package com.shop.productcatalogserver.service.product;

import com.shop.productcatalogserver.dto.product.IProductDTO;
import com.shop.productcatalogserver.dto.product.ProductDTO;
import com.shop.productcatalogserver.dto.product.ProductsExistsDTO;
import com.shop.productcatalogserver.model.category.Category;
import com.shop.productcatalogserver.model.product.Product;
import com.shop.productcatalogserver.model.product.description.ProductDescription;
import com.shop.productcatalogserver.model.product.option.OptionValue;
import com.shop.productcatalogserver.repository.category.CategoryRepository;
import com.shop.productcatalogserver.repository.product.ProductOptionRepository;
import com.shop.productcatalogserver.repository.product.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ProductRepository productRepo;
    @Autowired
    private ProductOptionRepository optionRepo;
    @Autowired
    private CategoryRepository categoryRepo;
//    @Autowired
//    private ShoppingCartProductItemRepository cartItemRepo;

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setSku(productDTO.getSku());
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
        Set<OptionValue> options = productDTO.getOptions().stream()
                .map(option -> {
                    try {
                        return optionRepo.findOptionValueByTypeAndValue(option.getOption(), option.getValue())
                                .orElseThrow(() -> new EntityNotFoundException("Option is not found"));
                    } catch (EntityNotFoundException ex) {
                        return new OptionValue(option.getOption(), option.getValue());
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

//        int orderProductsCount = productRepo.findCountOrderItems(id).orElse(0);
//        if (orderProductsCount > 0) {
//            throw new IllegalArgumentException("The product is contained in the completed orders");
//        }

//        List<ShoppingCartProductItem> productItems = productRepo.findCartItems(id).orElseGet(Collections::emptyList);
//        if (!productItems.isEmpty()) {
//            productItems.forEach(cartItemRepo::delete);
//        }

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

    public Page<IProductDTO> findByPathWithCategoryIds(String path, Pageable page) {
        return productRepo.findByPathWithCategoryIds(path, page);
    }

    @Override
    public ProductsExistsDTO isExists(List<Long> sku) {
        List<Long> exists = productRepo.checkExists(sku);
        sku.removeAll(exists);
        return new ProductsExistsDTO(exists, sku);
    }
}
