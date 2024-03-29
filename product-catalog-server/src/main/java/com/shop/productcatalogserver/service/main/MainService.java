package com.shop.productcatalogserver.service.main;

import com.shop.productcatalogserver.dto.main.MainConfigDTO;
import com.shop.productcatalogserver.dto.main.MainResponse;
import com.shop.productcatalogserver.model.category.Category;
import com.shop.productcatalogserver.model.product.Product;
import com.shop.productcatalogserver.repository.category.CategoryRepository;
import com.shop.productcatalogserver.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class MainService implements IMainService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductService productService;

    public MainResponse getByConfig(MainConfigDTO config) {

        MainResponse response = new MainResponse();

        if (config.isWithCategories()) {
            List<Category> categories = categoryRepo.findByDepth(config.getCategoryLevel())
                    .orElseThrow(() -> new EntityNotFoundException("Categories is not found"));
            response.setCategories(categories);
        }

        if (config.isWithProductsPopular()) {
            List<Product> products = productService.getPopular();
            response.setProductsPopular(products);
        }

        return response;
    }
}
