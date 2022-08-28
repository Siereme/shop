package app.controller.shoppingCart;

import app.consrtructor.TestShoppingCartConstructor;
import app.model.shoppingCart.ShoppingCart;
import app.model.shoppingCart.ShoppingCartProductItem;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<ShoppingCart> cartList = new ArrayList<>();
    private final String url = "/api/v1/shopping-cart";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String token;

    @BeforeAll
    void setUp() throws Exception {
        cartList.addAll(new TestShoppingCartConstructor().getAll());

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
        Assertions.assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<ShoppingCart>> typeRef = new TypeReference<>() {
        };
        List<ShoppingCart> carts = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(3, carts.size());
        Assertions.assertEquals(cartList.get(0).getId(), carts.get(0).getId());
        Assertions.assertEquals(cartList.get(0).getTotal(), carts.get(0).getTotal());
        Assertions.assertEquals(cartList.get(0).getCount(), carts.get(0).getCount());
        Assertions.assertEquals(cartList.get(0).getCartItems().size(), carts.get(0).getCartItems().size());

        Assertions.assertEquals(cartList.get(1).getId(), carts.get(1).getId());
        Assertions.assertEquals(cartList.get(1).getTotal(), carts.get(1).getTotal());
        Assertions.assertEquals(cartList.get(1).getCount(), carts.get(1).getCount());
        Assertions.assertEquals(cartList.get(1).getCartItems().size(), carts.get(1).getCartItems().size());
    }

    @Test
    @Order(2)
    void findByUserId() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/user-id/2")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        ShoppingCart cart = objectMapper.readValue(strContents, ShoppingCart.class);

        //Check the resulting object
        Assertions.assertEquals(cartList.get(1).getId(), cart.getId());
        Assertions.assertEquals(cartList.get(1).getTotal(), cart.getTotal());
        Assertions.assertEquals(cartList.get(1).getCount(), cart.getCount());
        Assertions.assertEquals(cartList.get(1).getCartItems().size(), cart.getCartItems().size());
    }

    @Test
    @Order(3)
    void addToCart() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/add?userId=1&productId=8&count=10")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        ShoppingCartProductItem cartProductItem = objectMapper.readValue(strContents, ShoppingCartProductItem.class);

        //Check the resulting object
        Assertions.assertEquals(8, cartProductItem.getProduct().getId());
        Assertions.assertEquals(10, cartProductItem.getCount());
    }

    @Test
    @Order(4)
    void removeCartItem() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/delete?userId=2&productId=4")
                        .header(accessHeader, token))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }
}