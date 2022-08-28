package app.controller.authentication;

import app.model.dto.authentication.AuthenticationRequest;
import app.model.dto.user.AuthenticationUserResponse;
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

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String url = "/api/v1/auth";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String accessToken;

    @Value("${jwt.refreshHeader}")
    private String refreshHeader;
    private String refreshToken;


    @Test
    @Order(1)
    AuthenticationUserResponse authenticate() throws Exception {
        //Prepare request
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("admin@mail.com");
        request.setPassword("Admin1");

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        AuthenticationUserResponse response = objectMapper.readValue(strContents, AuthenticationUserResponse.class);

        //Check the resulting object
        Assertions.assertNotNull(response.getAccessToken());
        Assertions.assertNotNull(response.getRefreshToken());
        Assertions.assertNotNull(response.getUser());

        return response;
    }

    @Test
    @Order(2)
    void anonymousAuthenticate() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/login-anonymous")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        AuthenticationUserResponse response = objectMapper.readValue(strContents, AuthenticationUserResponse.class);

        //Check the resulting object
        Assertions.assertNotNull(response.getAccessToken());
        Assertions.assertNotNull(response.getRefreshToken());
        Assertions.assertNotNull(response.getUser());
    }

    @Test
    @Order(3)
    void refreshAuthenticate() throws Exception {
        //Get refresh token
        refreshToken = authenticate().getRefreshToken();

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/refresh")
                        .header(refreshHeader, refreshToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        AuthenticationUserResponse response = objectMapper.readValue(strContents, AuthenticationUserResponse.class);

        //Check the resulting object
        Assertions.assertNotNull(response.getAccessToken());
        Assertions.assertNotNull(response.getRefreshToken());
        Assertions.assertNotNull(response.getUser());
    }

    @Test
    @Order(5)
    void logout() throws Exception {
        //Get access token
        accessToken = authenticate().getAccessToken();

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/logout")
                        .header(accessHeader, accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(4)
    void info() throws Exception {
        //Get access token
        accessToken = authenticate().getAccessToken();

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/info")
                        .header(accessHeader, accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        AuthenticationUserResponse response = objectMapper.readValue(strContents, AuthenticationUserResponse.class);

        //Check the resulting object
        Assertions.assertNotNull(response.getAccessToken());
        Assertions.assertNotNull(response.getUser());
    }
}