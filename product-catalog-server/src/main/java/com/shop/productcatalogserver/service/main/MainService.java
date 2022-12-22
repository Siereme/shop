package com.shop.productcatalogserver.service.main;

import com.shop.productcatalogserver.dto.main.MainConfigDTO;
import com.shop.productcatalogserver.dto.main.MainResponse;
import com.shop.productcatalogserver.model.category.Category;
import com.shop.productcatalogserver.model.product.Product;
import com.shop.productcatalogserver.repository.category.CategoryRepository;
import com.shop.productcatalogserver.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MainService implements IMainService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductService productService;

    public MainResponse getByConfig(MainConfigDTO config) {
        if (config.getUserId() == null) {
            throw new IllegalArgumentException("User id is not set");
        }

        MainResponse response = new MainResponse();

        if (config.isWithCategories()) {
            List<Category> categories = categoryRepo.findByDepth(config.getCategoryLevel())
                    .orElseThrow(() -> new EntityNotFoundException("Categories is not found"));
            response.setCategories(categories);
        }

//        if (config.isWithShoppingCart()) {
//            ShoppingCart shoppingCart = cartRepo.findByUserId(config.getUserId())
//                    .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
//            response.setShoppingCart(shoppingCart);
//        }

//        if (config.isWithOrders()) {
//            List<Order> orders = orderRepo.findAllByUserId(config.getUserId()).stream()
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList());
//            response.setOrders(orders);
//        }

        if (config.isWithProductsPopular()) {
            List<Product> products = productService.getPopular();
            response.setProductsPopular(products);
        }

        return response;
    }
}
