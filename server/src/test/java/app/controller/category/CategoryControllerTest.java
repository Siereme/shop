package app.controller.category;

import app.consrtructor.TestCategoryConstructor;
import app.model.category.Category;
import app.model.dto.category.CategoryDTO;
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
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Category> categoryList = new ArrayList<>();
    private final String url = "/api/v1/category";

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    private String token;

    @BeforeAll
    void setUp() throws Exception {
        categoryList.addAll(new TestCategoryConstructor().getAll());

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
        TypeReference<List<Category>> typeRef = new TypeReference<>() {
        };
        List<Category> categories = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(categoryList.size(), categories.size());
        Assertions.assertEquals(categoryList.get(0).getId(), categories.get(0).getId());
        Assertions.assertEquals(categoryList.get(0).getName(), categories.get(0).getName());
        Assertions.assertEquals(categoryList.get(0).getCategories().size(), categories.get(0).getCategories().size());
        Assertions.assertEquals(categoryList.get(0).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(categoryList.get(0).getDepth(), categories.get(0).getDepth());

        Assertions.assertEquals(categoryList.get(4).getId(), categories.get(4).getId());
        Assertions.assertEquals(categoryList.get(4).getName(), categories.get(4).getName());
        Assertions.assertEquals(categoryList.get(4).getCategories().size(), categories.get(4).getCategories().size());
        Assertions.assertEquals(categoryList.get(4).getLineage(), categories.get(4).getLineage());
        Assertions.assertEquals(categoryList.get(4).getDepth(), categories.get(4).getDepth());
    }

    @Test
    @Order(2)
    void findById() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/id/1")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        Category category = objectMapper.readValue(strContents, Category.class);

        //Check the resulting object
        Assertions.assertEquals(categoryList.get(0).getId(), category.getId());
        Assertions.assertEquals(categoryList.get(0).getName(), category.getName());
        Assertions.assertEquals(categoryList.get(0).getCategories().size(), category.getCategories().size());
        Assertions.assertEquals(categoryList.get(0).getLineage(), category.getLineage());
        Assertions.assertEquals(categoryList.get(0).getDepth(), category.getDepth());
        Assertions.assertNull(category.getParent());
    }

    @Test
    @Order(3)
    void findByIds() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/ids/2,4")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Category>> typeRef = new TypeReference<>() {
        };
        List<Category> categories = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(2, categories.size());
        Assertions.assertEquals(categoryList.get(1).getId(), categories.get(0).getId());
        Assertions.assertEquals(categoryList.get(1).getName(), categories.get(0).getName());
        Assertions.assertEquals(categoryList.get(1).getCategories().size(), categories.get(0).getCategories().size());
        Assertions.assertEquals(categoryList.get(1).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(categoryList.get(1).getDepth(), categories.get(0).getDepth());

        Assertions.assertEquals(categoryList.get(3).getId(), categories.get(1).getId());
        Assertions.assertEquals(categoryList.get(3).getName(), categories.get(1).getName());
        Assertions.assertEquals(categoryList.get(3).getCategories().size(), categories.get(1).getCategories().size());
        Assertions.assertEquals(categoryList.get(3).getLineage(), categories.get(1).getLineage());
        Assertions.assertEquals(categoryList.get(3).getDepth(), categories.get(1).getDepth());
    }

    @Test
    @Order(4)
    void findAllFirstLevel() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/first-level")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Category>> typeRef = new TypeReference<>() {
        };
        List<Category> categories = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(categoryList.get(0).getId(), categories.get(0).getId());
        Assertions.assertEquals(categoryList.get(0).getName(), categories.get(0).getName());
        Assertions.assertEquals(categoryList.get(0).getCategories().size(), categories.get(0).getCategories().size());
        Assertions.assertEquals(categoryList.get(0).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(categoryList.get(0).getDepth(), categories.get(0).getDepth());
    }

    @Test
    @Order(5)
    void findByDepth() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/depth?depth=2")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Category>> typeRef = new TypeReference<>() {
        };
        List<Category> categories = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(4, categories.size());
        Assertions.assertEquals(categoryList.get(1).getId(), categories.get(0).getId());
        Assertions.assertEquals(categoryList.get(1).getName(), categories.get(0).getName());
        Assertions.assertEquals(categoryList.get(1).getCategories().size(), categories.get(0).getCategories().size());
        Assertions.assertEquals(categoryList.get(1).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(categoryList.get(1).getDepth(), categories.get(0).getDepth());

        Assertions.assertEquals(categoryList.get(3).getId(), categories.get(2).getId());
        Assertions.assertEquals(categoryList.get(3).getName(), categories.get(2).getName());
        Assertions.assertEquals(categoryList.get(3).getCategories().size(), categories.get(2).getCategories().size());
        Assertions.assertEquals(categoryList.get(3).getLineage(), categories.get(2).getLineage());
        Assertions.assertEquals(categoryList.get(3).getDepth(), categories.get(2).getDepth());
    }

    @Test
    @Order(6)
    void findByLineageAndDepth() throws Exception {
        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .get(url + "/lineage-depth?lineage=1&depth=2")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an objects
        String strContents = mvcResult.getResponse().getContentAsString();
        TypeReference<List<Category>> typeRef = new TypeReference<>() {
        };
        List<Category> categories = objectMapper.readValue(strContents, typeRef);

        //Check the resulting objects
        Assertions.assertEquals(4, categories.size());
        Assertions.assertEquals(categoryList.get(1).getId(), categories.get(0).getId());
        Assertions.assertEquals(categoryList.get(1).getName(), categories.get(0).getName());
        Assertions.assertEquals(categoryList.get(1).getCategories().size(), categories.get(0).getCategories().size());
        Assertions.assertEquals(categoryList.get(1).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(categoryList.get(1).getDepth(), categories.get(0).getDepth());

        Assertions.assertEquals(categoryList.get(3).getId(), categories.get(2).getId());
        Assertions.assertEquals(categoryList.get(3).getName(), categories.get(2).getName());
        Assertions.assertEquals(categoryList.get(3).getCategories().size(), categories.get(2).getCategories().size());
        Assertions.assertEquals(categoryList.get(3).getLineage(), categories.get(2).getLineage());
        Assertions.assertEquals(categoryList.get(3).getDepth(), categories.get(2).getDepth());
    }

    @Test
    @Order(7)
    void addProduct() throws Exception {
        //Prepare request object
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Чехлы для смартфонов");
        categoryDTO.setParentId(1L);

        //Send a request
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders
                        .post(url + "/add")
                        .header(accessHeader, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andReturn();

        //Check the response status
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        //Convert the response to an object
        String strContents = mvcResult.getResponse().getContentAsString();
        Category category = objectMapper.readValue(strContents, Category.class);

        //Check the resulting object
        Assertions.assertEquals(categoryDTO.getName(), category.getName());
    }

    @Test
    @Order(8)
    void deleteCategory() throws Exception {
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