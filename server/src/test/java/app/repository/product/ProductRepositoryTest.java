package app.repository.product;

import app.consrtructor.TestProductConstructor;
import app.model.product.Product;
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
        Assertions.assertEquals(checkProductList.get(0).getName(), products.get(0).getName());
        Assertions.assertEquals(checkProductList.get(0).getImageLink(), products.get(0).getImageLink());
        Assertions.assertEquals(checkProductList.get(0).getCategories().size(), products.get(0).getCategories().size());
        Assertions.assertEquals(checkProductList.get(0).getCategories().iterator().next().getId(), products.get(0).getCategories().iterator().next().getId());

        //Comparison of the third elements of collections
        Assertions.assertEquals(checkProductList.get(3).getId(), products.get(3).getId());
        Assertions.assertEquals(checkProductList.get(3).getArticle(), products.get(3).getArticle());
        Assertions.assertEquals(checkProductList.get(3).getName(), products.get(3).getName());
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
        Assertions.assertEquals(checkUser.getName(), product.getName());
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
        Assertions.assertEquals(checkProductList.get(0).getName(), products.get(0).getName());
        Assertions.assertEquals(checkProductList.get(0).getImageLink(), products.get(0).getImageLink());
        Assertions.assertEquals(checkProductList.get(0).getCategories().size(), products.get(0).getCategories().size());
        Assertions.assertEquals(checkProductList.get(0).getCategories().iterator().next().getId(), products.get(0).getCategories().iterator().next().getId());

        //Comparison of the second elements of collections
        Assertions.assertEquals(checkProductList.get(1).getId(), products.get(1).getId());
        Assertions.assertEquals(checkProductList.get(1).getArticle(), products.get(1).getArticle());
        Assertions.assertEquals(checkProductList.get(1).getName(), products.get(1).getName());
        Assertions.assertEquals(checkProductList.get(1).getImageLink(), products.get(1).getImageLink());
        Assertions.assertEquals(checkProductList.get(1).getCategories().size(), products.get(1).getCategories().size());
        Assertions.assertEquals(checkProductList.get(1).getCategories().iterator().next().getId(), products.get(1).getCategories().iterator().next().getId());
    }

    @Test
    void findPopularIds() {
    }

    @Test
    void findByCategoryId() {

        //Find products from repository where categoryId = 3
        List<Product> products = productRepo.findByLineageDepthAndCategoryId(1L, 2, 3L).orElseGet(Collections::emptyList);

        //Get products from constructor where categoryId = 3
        List<Product> checkProductList = List.of(productList.get(2), productList.get(3));

        //Check collections
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(checkProductList.size(), products.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkProductList.get(0).getId(), products.get(0).getId());
        Assertions.assertEquals(checkProductList.get(0).getArticle(), products.get(0).getArticle());
        Assertions.assertEquals(checkProductList.get(0).getName(), products.get(0).getName());
        Assertions.assertEquals(checkProductList.get(0).getImageLink(), products.get(0).getImageLink());
        Assertions.assertEquals(checkProductList.get(0).getCategories().size(), products.get(0).getCategories().size());
        Assertions.assertEquals(checkProductList.get(0).getCategories().iterator().next().getId(), products.get(0).getCategories().iterator().next().getId());

        //Comparison of the second elements of collections
        Assertions.assertEquals(checkProductList.get(1).getId(), products.get(1).getId());
        Assertions.assertEquals(checkProductList.get(1).getArticle(), products.get(1).getArticle());
        Assertions.assertEquals(checkProductList.get(1).getName(), products.get(1).getName());
        Assertions.assertEquals(checkProductList.get(1).getImageLink(), products.get(1).getImageLink());
        Assertions.assertEquals(checkProductList.get(1).getCategories().size(), products.get(1).getCategories().size());
        Assertions.assertEquals(checkProductList.get(1).getCategories().iterator().next().getId(), products.get(1).getCategories().iterator().next().getId());
    }
}