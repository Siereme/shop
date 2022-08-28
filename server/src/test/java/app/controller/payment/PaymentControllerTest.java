package app.controller.payment;

import app.model.order.payment.Payment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
class PaymentControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Payment> paymentList = new ArrayList<>();
    private final String url = "/api/v1/payment";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String token;

    @BeforeAll
    void setUp() throws Exception {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setType("При получении");
        paymentList.add(payment);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login-anonymous")).andReturn();
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> contents = objectMapper.readValue(strContents, typeRef);
        this.token = contents.get("accessToken").toString();
    }

    @Test
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
        TypeReference<List<Payment>> typeRef = new TypeReference<>() {
        };
        List<Payment> payments = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(paymentList.size(), payments.size());
        Assertions.assertEquals(paymentList.get(0).getId(), payments.get(0).getId());
        Assertions.assertEquals(paymentList.get(0).getType(), payments.get(0).getType());
    }
}