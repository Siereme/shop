package app.controller.product;

import app.consrtructor.TestProductConstructor;
import app.model.category.Category;
import app.model.dto.product.ProductDTO;
import app.model.product.Product;
import app.repository.shoppingCart.ShoppingCartRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Product> productList = new ArrayList<>();
    private final String url = "/api/v1/product";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String token;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @BeforeAll
    void setUp() throws Exception {
        productList.addAll(new TestProductConstructor().getAll());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login-anonymous")).andReturn();
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> contents = objectMapper.readValue(strContents, typeRef);
        this.token = contents.get("accessToken").toString();
    }

    @Test
    @Order(1)
    void findAll() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/all")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Product>> typeRef = new TypeReference<>() {
        };
        List<Product> products = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        assertEquals(productList.size(), products.size());
        assertEquals(productList.get(0).getId(), products.get(0).getId());
        assertEquals(productList.get(0).getArticle(), products.get(0).getArticle());
        assertEquals(productList.get(0).getName(), products.get(0).getName());
        assertEquals(productList.get(0).getCategories().size(), products.get(0).getCategories().size());

        assertEquals(productList.get(4).getId(), products.get(4).getId());
        assertEquals(productList.get(4).getArticle(), products.get(4).getArticle());
        assertEquals(productList.get(4).getName(), products.get(4).getName());
        assertEquals(productList.get(4).getCategories().size(), products.get(4).getCategories().size());
    }

    @Test
    @Order(2)
    void findById() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/id/4")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        Product product = objectMapper.readValue(strContents, Product.class);

        //Check the resulting object
        assertEquals(productList.get(3).getId(), product.getId());
        assertEquals(productList.get(3).getArticle(), product.getArticle());
        assertEquals(productList.get(3).getName(), product.getName());
        assertEquals(productList.get(3).getCategories().size(), product.getCategories().size());
    }

    @Test
    @Order(3)
    void findByIds() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/ids/1,5")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Product>> typeRef = new TypeReference<>() {
        };
        List<Product> products = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        assertEquals(2, products.size());
        assertEquals(productList.get(0).getId(), products.get(0).getId());
        assertEquals(productList.get(0).getArticle(), products.get(0).getArticle());
        assertEquals(productList.get(0).getName(), products.get(0).getName());
        assertEquals(productList.get(0).getCategories().size(), products.get(0).getCategories().size());

        assertEquals(productList.get(4).getId(), products.get(1).getId());
        assertEquals(productList.get(4).getArticle(), products.get(1).getArticle());
        assertEquals(productList.get(4).getName(), products.get(1).getName());
        assertEquals(productList.get(4).getCategories().size(), products.get(1).getCategories().size());
    }

    @Test
    @Order(4)
    void findByCategoryId() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/category-id/3")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Product>> typeRef = new TypeReference<>() {
        };
        List<Product> products = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        assertEquals(2, products.size());
        assertEquals(productList.get(2).getId(), products.get(0).getId());
        assertEquals(productList.get(2).getArticle(), products.get(0).getArticle());
        assertEquals(productList.get(2).getName(), products.get(0).getName());
        assertEquals(productList.get(2).getCategories().size(), products.get(0).getCategories().size());

        assertEquals(productList.get(3).getId(), products.get(1).getId());
        assertEquals(productList.get(3).getArticle(), products.get(1).getArticle());
        assertEquals(productList.get(3).getName(), products.get(1).getName());
        assertEquals(productList.get(3).getCategories().size(), products.get(1).getCategories().size());
    }

    @Test
    @Order(5)
    void findPopularProducts() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/popular")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Product>> typeRef = new TypeReference<>() {
        };
        List<Product> products = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        assertTrue(10 >= products.size());
        assertEquals(productList.get(0).getId(), products.get(0).getId());
        assertEquals(productList.get(0).getArticle(), products.get(0).getArticle());
        assertEquals(productList.get(0).getName(), products.get(0).getName());
        assertEquals(productList.get(0).getCategories().size(), products.get(0).getCategories().size());

        assertEquals(productList.get(3).getId(), products.get(3).getId());
        assertEquals(productList.get(3).getArticle(), products.get(3).getArticle());
        assertEquals(productList.get(3).getName(), products.get(3).getName());
        assertEquals(productList.get(3).getCategories().size(), products.get(3).getCategories().size());
    }

    @Test
    @Order(6)
    void addProduct() throws Exception {
        //Prepare request objects
        ProductDTO productDTO = new ProductDTO();
        productDTO.setArticle(productList.get(5).getArticle());
        productDTO.setName(productList.get(5).getName());
        productDTO.setPrice(productList.get(5).getPrice());
        productDTO.setImageLink(productList.get(5).getImageLink());
        productDTO.setOptions(productList.get(5).getOptions());
        Set<Long> ids = productList.get(5).getCategories().stream().map(Category::getId).collect(Collectors.toSet());
        productDTO.setCategoriesIds(ids);

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/add")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        Product product = objectMapper.readValue(strContents, Product.class);

        //Check the resulting object
        assertEquals(productList.get(5).getArticle(), product.getArticle());
        assertEquals(productList.get(5).getName(), product.getName());
        assertEquals(productList.get(5).getPrice(), product.getPrice());
        assertEquals(productList.get(5).getImageLink(), product.getImageLink());
        assertEquals(productList.get(5).getCategories().size(), product.getCategories().size());
    }

    @Test
    @Order(7)
    void deleteProduct() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/delete/2")
                        .header(accessHeader, token))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }
}