package app.service.main;

import app.model.category.Category;
import app.model.dto.main.MainConfigDTO;
import app.model.dto.main.MainResponse;
import app.model.order.Order;
import app.model.product.Product;
import app.model.shoppingCart.ShoppingCart;
import app.repository.category.CategoryRepository;
import app.repository.order.OrderRepository;
import app.repository.product.ProductRepository;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MainService implements IMainService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ProductService productService;

    public MainResponse getByConfig(MainConfigDTO config) {
        if(config.getUserId() == null){
            throw new IllegalArgumentException("User id is not set");
        }

        MainResponse response = new MainResponse();

        if(config.isWithCategories()){
            List<Category> categories =  categoryRepo.findByDepth(config.getCategoryLevel())
                    .orElseThrow(() -> new EntityNotFoundException("Categories is not found"));
            response.setCategories(categories);
        }

        if(config.isWithShoppingCart()){
            ShoppingCart shoppingCart = cartRepo.findByUserId(config.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("Shopping cart is not found"));
            response.setShoppingCart(shoppingCart);
        }

        if(config.isWithOrders()){
            List<Order> orders = orderRepo.findAllByUserId(config.getUserId()).stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            response.setOrders(orders);
        }

        if(config.isWithProductsPopular()){
            List<Product> products = productService.getPopular();
            response.setProductsPopular(products);
        }

        return response;
    }
}
