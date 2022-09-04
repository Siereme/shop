package app.service.category;

import app.consrtructor.TestCategoryConstructor;
import app.model.category.Category;
import app.model.dto.category.CategoryRequest;
import app.repository.category.CategoryRepository;
import app.repository.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepo;
    @Mock
    private ProductRepository productRepo;

    private final TestCategoryConstructor constructor = new TestCategoryConstructor();

    @Test
    void addCategory() {
        //Prepare objects
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(10L);
        categoryRequest.setName("Проекторы");
        categoryRequest.setParentId(3L);

        Category categoryMock = new Category();
        categoryMock.setId(10L);
        categoryMock.setName("Проекторы");
        categoryMock.setParent(constructor.getById(3L));

        //When stubs are called
        Optional<Category> foundCategory = Optional.of(constructor.getById(3L));
        Mockito.when(categoryRepo.findById(categoryRequest.getParentId())).thenReturn(foundCategory);
        Mockito.when(categoryRepo.save(any(Category.class))).thenReturn(categoryMock);

        //Call a real service method
        Category category = categoryService.addCategory(categoryRequest);

        //Verify stub calls
        Mockito.verify(categoryRepo, Mockito.times(1)).findById(categoryRequest.getParentId());
        Mockito.verify(categoryRepo, Mockito.times(1)).save(any(Category.class));

        //Check the resulting object
        Assertions.assertEquals(categoryMock.getId(), category.getId());
        Assertions.assertEquals(categoryMock.getName(), category.getName());
        Assertions.assertEquals(categoryMock.getParent().getId(), category.getParent().getId());
    }

    @Test
    void testDeleteById() {
        //When stubs are called
        Mockito.when(categoryRepo.findByIdWithProducts(2L)).thenReturn(Optional.of(constructor.getById(2L)));
        Mockito.doNothing().when(categoryRepo).delete(any(Category.class));

        //Call a real service method
        categoryService.deleteById(2L);

        //Verify stub calls
        Mockito.verify(categoryRepo, Mockito.times(1)).findByIdWithProducts(2L);
        Mockito.verify(categoryRepo, Mockito.times(1)).delete(any(Category.class));
    }
}