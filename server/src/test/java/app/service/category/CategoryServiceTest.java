package app.service.category;

import app.consrtructor.TestCategoryConstructor;
import app.model.category.Category;
import app.model.dto.category.CategoryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CategoryServiceTest {

    @Mock
    private CategoryService categoryService;
    private final TestCategoryConstructor constructor = new TestCategoryConstructor();

    @Test
    void addCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(10L);
        categoryDTO.setName("Проекторы");
        categoryDTO.setParentId(3L);

        Category categoryMock = new Category();
        categoryMock.setId(10L);
        categoryMock.setName("Проекторы");
        categoryMock.setParent(constructor.getById(3L));

        Mockito.when(categoryService.addCategory(categoryDTO)).thenReturn(categoryMock);

        Category category = categoryService.addCategory(categoryDTO);

        Assertions.assertEquals(categoryMock.getId(), category.getId());
        Assertions.assertEquals(categoryMock.getName(), category.getName());
        Assertions.assertEquals(categoryMock.getParent().getId(), category.getParent().getId());
    }
}