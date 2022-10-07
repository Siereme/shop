package app.repository.product;

import app.consrtructor.TestProductConstructor;
import app.model.product.Product;
import app.model.shoppingCart.ShoppingCartProductItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepo;
    private final List<Product> productList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        productList.addAll(new TestProductConstructor().getAll());
    }

    @Test
    void findAll() {
        //Find all products from repository
        List<Product> products = productRepo.findAll();

        //Get all products from constructor
        List<Product> checkProductList = productList;

        //Check collections
        Assertions.assertEquals(checkProductList.size(), products.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkProductList.get(0).getId(), products.get(0).getId());
        Assertions.assertEquals(checkProductList.get(0).getArticle(), products.get(0).getArticle());
        Assertions.assertEquals(checkProductList.get(0).getTitle(), products.get(0).getTitle());
        Assertions.assertEquals(checkProductList.get(0).getImageLink(), products.get(0).getImageLink());
        Assertions.assertEquals(checkProductList.get(0).getCategories().size(), products.get(0).getCategories().size());
        Assertions.assertEquals(checkProductList.get(0).getCategories().iterator().next().getId(), products.get(0).getCategories().iterator().next().getId());

        //Comparison of the third elements of collections
        Assertions.assertEquals(checkProductList.get(3).getId(), products.get(3).getId());
        Assertions.assertEquals(checkProductList.get(3).getArticle(), products.get(3).getArticle());
        Assertions.assertEquals(checkProductList.get(3).getTitle(), products.get(3).getTitle());
        Assertions.assertEquals(checkProductList.get(3).getImageLink(), products.get(3).getImageLink());
        Assertions.assertEquals(checkProductList.get(3).getCategories().size(), products.get(3).getCategories().size());
        Assertions.assertEquals(checkProductList.get(3).getCategories().iterator().next().getId(), products.get(3).getCategories().iterator().next().getId());
    }

    @Test
    void findById() {
        //Find product from repository where id = 6
        Product product = productRepo.findById(6L).orElseGet(Product::new);

        //Get product from constructor where id = 6
        Product checkUser = productList.get(5);

        //Comparison of entities
        Assertions.assertEquals(checkUser.getId(), product.getId());
        Assertions.assertEquals(checkUser.getArticle(), product.getArticle());
        Assertions.assertEquals(checkUser.getTitle(), product.getTitle());
        Assertions.assertEquals(checkUser.getImageLink(), product.getImageLink());
        Assertions.assertEquals(checkUser.getCategories().size(), product.getCategories().size());
        Assertions.assertEquals(checkUser.getCategories().iterator().next().getId(), product.getCategories().iterator().next().getId());
    }

    @Test
    void findAllById() {

        //Find products from repository where id in (5,7)
        List<Product> products = productRepo.findAllById(List.of(5L, 7L));

        //Get products from constructor where id in (5,7)
        List<Product> checkProductList = List.of(productList.get(4), productList.get(6));

        //Check collections
        Assertions.assertEquals(checkProductList.size(), products.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkProductList.get(0).getId(), products.get(0).getId());
        Assertions.assertEquals(checkProductList.get(0).getArticle(), products.get(0).getArticle());
        Assertions.assertEquals(checkProductList.get(0).getTitle(), products.get(0).getTitle());
        Assertions.assertEquals(checkProductList.get(0).getImageLink(), products.get(0).getImageLink());
        Assertions.assertEquals(checkProductList.get(0).getCategories().size(), products.get(0).getCategories().size());
        Assertions.assertEquals(checkProductList.get(0).getCategories().iterator().next().getId(), products.get(0).getCategories().iterator().next().getId());

        //Comparison of the second elements of collections
        Assertions.assertEquals(checkProductList.get(1).getId(), products.get(1).getId());
        Assertions.assertEquals(checkProductList.get(1).getArticle(), products.get(1).getArticle());
        Assertions.assertEquals(checkProductList.get(1).getTitle(), products.get(1).getTitle());
        Assertions.assertEquals(checkProductList.get(1).getImageLink(), products.get(1).getImageLink());
        Assertions.assertEquals(checkProductList.get(1).getCategories().size(), products.get(1).getCategories().size());
        Assertions.assertEquals(checkProductList.get(1).getCategories().iterator().next().getId(), products.get(1).getCategories().iterator().next().getId());
    }

    @Test
    void findPopularIds() {
    }

    @Test
    void testFindCountOrderItems() {
        //Find count order product items from repository where productId = 1
        int count = productRepo.findCountOrderItems(1L).orElse(0);

        //Check count
        Assertions.assertEquals(1, count);
    }

    @Test
    void testFindCartItems() {
        //Find cart items from repository where productId = 1
        List<ShoppingCartProductItem> cartItems = productRepo.findCartItems(1L).orElseGet(Collections::emptyList);

        //Check count
        Assertions.assertEquals(1, cartItems.size());

        //Check first element of the collection
        Assertions.assertEquals(1, cartItems.get(0).getProduct().getId());
        Assertions.assertEquals(7, cartItems.get(0).getCount());
    }

}