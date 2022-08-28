package app.controller.order;

import app.consrtructor.TestOrderConstructor;
import app.model.dto.order.OrderDTO;
import app.model.dto.order.OrderResponseDTO;
import app.model.order.Order;
import app.model.order.payment.Payment;
import app.model.user.User;
import app.repository.order.PaymentRepository;
import app.repository.user.UserRepository;
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
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    private final List<Order> orderList = new ArrayList<>();
    private final String url = "/api/v1/order";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String token;

    @BeforeAll
    void setUp() throws Exception {
        orderList.addAll(new TestOrderConstructor().getAll());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login-anonymous")).andReturn();
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> contents = objectMapper.readValue(strContents, typeRef);
        this.token = contents.get("accessToken").toString();
    }


    @Test
    @org.junit.jupiter.api.Order(1)
    void getOrders() throws Exception {
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
        TypeReference<List<Order>> typeRef = new TypeReference<>() {
        };
        List<Order> orders = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(orderList.size(), orders.size());
        Assertions.assertEquals(orderList.get(0).getId(), orders.get(0).getId());
        Assertions.assertEquals(orderList.get(0).getUserDetails().getEmail(), orders.get(0).getUserDetails().getEmail());
        Assertions.assertEquals(orderList.get(0).getTotal(), orders.get(0).getTotal());
        Assertions.assertEquals(orderList.get(0).getOrderItems().size(), orders.get(0).getOrderItems().size());

        Assertions.assertEquals(orderList.get(1).getId(), orders.get(1).getId());
        Assertions.assertEquals(orderList.get(1).getUserDetails().getEmail(), orders.get(1).getUserDetails().getEmail());
        Assertions.assertEquals(orderList.get(1).getTotal(), orders.get(1).getTotal());
        Assertions.assertEquals(orderList.get(1).getOrderItems().size(), orders.get(1).getOrderItems().size());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void getOrdersByUserId() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/user-id/1")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Order>> typeRef = new TypeReference<>() {
        };
        List<Order> orders = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(orderList.get(0).getId(), orders.get(0).getId());
        Assertions.assertEquals(orderList.get(0).getUserDetails().getEmail(), orders.get(0).getUserDetails().getEmail());
        Assertions.assertEquals(orderList.get(0).getTotal(), orders.get(0).getTotal());
        Assertions.assertEquals(orderList.get(0).getOrderItems().size(), orders.get(0).getOrderItems().size());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void addOrder() throws Exception {
        //Prepare request objects
        User user = userRepo.findById(1L).orElseGet(User::new);
        Payment payment = paymentRepo.findById(1L).orElseGet(Payment::new);
        OrderDTO orderDTO = new OrderDTO(user, payment);

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/add")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        OrderResponseDTO response = objectMapper.readValue(strContents, OrderResponseDTO.class);

        //Check the resulting object
        Assertions.assertEquals(user.getEmail(), response.getOrder().getUserDetails().getEmail());
        Assertions.assertEquals(payment.getId(), response.getOrder().getPayment().getId());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void deleteOrder() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/delete/3")
                        .header(accessHeader, token))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }

}