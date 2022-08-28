package app.controller.user;

import app.consrtructor.TestUserConstructor;
import app.model.user.User;
import app.utils.constants.user.UserRole;
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
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<User> userList = new ArrayList<>();
    private final String url = "/api/v1/user";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String token;

    @BeforeAll
    void setUp() throws Exception {
        userList.addAll(new TestUserConstructor().getAll());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login-anonymous")).andReturn();
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> contents = objectMapper.readValue(strContents, typeRef);
        this.token = contents.get("accessToken").toString();
    }

    @Test
    @Order(1)
    void getUsers() throws Exception {
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
        TypeReference<List<User>> typeRef = new TypeReference<>() {
        };
        List<User> users = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(3, users.size());
        Assertions.assertEquals(userList.get(0).getId(), users.get(0).getId());
        Assertions.assertEquals(userList.get(0).getName(), users.get(0).getName());
        Assertions.assertEquals(userList.get(0).getEmail(), users.get(0).getEmail());
        Assertions.assertEquals(userList.get(0).getPhone(), users.get(0).getPhone());
        Assertions.assertEquals(userList.get(0).getRole().getName(), users.get(0).getRole().getName());

        Assertions.assertEquals(userList.get(1).getId(), users.get(1).getId());
        Assertions.assertEquals(userList.get(1).getName(), users.get(1).getName());
        Assertions.assertEquals(userList.get(1).getEmail(), users.get(1).getEmail());
        Assertions.assertEquals(userList.get(1).getPhone(), users.get(1).getPhone());
        Assertions.assertEquals(userList.get(1).getRole().getName(), users.get(1).getRole().getName());
    }

    @Test
    @Order(2)
    void getUserById() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/id/2")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        User user = objectMapper.readValue(strContents, User.class);

        //Check the resulting object
        Assertions.assertEquals(userList.get(1).getId(), user.getId());
        Assertions.assertEquals(userList.get(1).getName(), user.getName());
        Assertions.assertEquals(userList.get(1).getEmail(), user.getEmail());
        Assertions.assertEquals(userList.get(1).getPhone(), user.getPhone());
        Assertions.assertEquals(userList.get(1).getRole().getName(), user.getRole().getName());
    }

    @Test
    @Order(3)
    void addUser() throws Exception {
        //Prepare request object
        User user = new User();
        user.setEmail("admin2@mail.com");
        user.setPhone("+78888888888");
        user.setPassword("1234");

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/add")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        User newUser = objectMapper.readValue(strContents, User.class);

        //Check the resulting object
        Assertions.assertEquals(user.getName(), newUser.getName());
        Assertions.assertEquals(user.getEmail(), newUser.getEmail());
        Assertions.assertEquals(user.getPhone(), newUser.getPhone());
        Assertions.assertEquals(UserRole.USER.name(), newUser.getRole().getName());
    }

    @Test
    @Order(4)
    void addAnonymousUser() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/add/anonymous")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        User newUser = objectMapper.readValue(strContents, User.class);

        //Check the resulting object
        Assertions.assertNotNull(newUser.getEmail());
        Assertions.assertNotNull(newUser.getPassword());
    }
}