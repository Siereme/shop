package app.repository.category;

import app.consrtructor.TestCategoryConstructor;
import app.model.category.Category;
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
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepo;
    private final List<Category> categoryList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        categoryList.addAll(new TestCategoryConstructor().getAll());
    }

    @Test
    void testFindAllFirstLevel() {
        //Find first-level categories from repository
        List<Category> categories = categoryRepo.findAllFirstLevel().orElseGet(Collections::emptyList);

        //Get first-level categories from constructor
        List<Category> checkCategoryList = List.of(categoryList.get(0));

        //Check collections
        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(checkCategoryList.size(), categories.size());

        //Comparison of the first elements of collections
        Assertions.assertNull(categories.get(0).getParent());
        Assertions.assertEquals(checkCategoryList.get(0).getId(), categories.get(0).getId());
        Assertions.assertEquals(checkCategoryList.get(0).getName(), categories.get(0).getName());
        Assertions.assertEquals(checkCategoryList.get(0).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(checkCategoryList.get(0).getDepth(), categories.get(0).getDepth());
        Assertions.assertEquals(checkCategoryList.get(0).getCategories().size(), categories.get(0).getCategories().size());
    }

    @Test
    void testFindByLineageAndDepth() {
        //Find categories from repository where lineage = 1 and depth = 2
        List<Category> categories = categoryRepo.findByLineageAndDepth(1L, 2).orElseGet(Collections::emptyList);

        //Get categories from constructor where lineage = 1 and depth = 2
        List<Category> checkCategoryList = List.of(
                categoryList.get(1),
                categoryList.get(2),
                categoryList.get(3),
                categoryList.get(4)
        );

        //Check collections
        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(checkCategoryList.size(), categories.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkCategoryList.get(0).getId(), categories.get(0).getId());
        Assertions.assertEquals(checkCategoryList.get(0).getName(), categories.get(0).getName());
        Assertions.assertEquals(checkCategoryList.get(0).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(checkCategoryList.get(0).getDepth(), categories.get(0).getDepth());
        Assertions.assertEquals(checkCategoryList.get(0).getCategories().size(), categories.get(0).getCategories().size());

        //Comparison of the second elements of collections
        Assertions.assertEquals(checkCategoryList.get(1).getId(), categories.get(1).getId());
        Assertions.assertEquals(checkCategoryList.get(1).getName(), categories.get(1).getName());
        Assertions.assertEquals(checkCategoryList.get(1).getLineage(), categories.get(1).getLineage());
        Assertions.assertEquals(checkCategoryList.get(1).getDepth(), categories.get(1).getDepth());
        Assertions.assertEquals(checkCategoryList.get(1).getCategories().size(), categories.get(1).getCategories().size());

        //Comparison of the third elements of collections
        Assertions.assertEquals(checkCategoryList.get(2).getId(), categories.get(2).getId());
        Assertions.assertEquals(checkCategoryList.get(2).getName(), categories.get(2).getName());
        Assertions.assertEquals(checkCategoryList.get(2).getLineage(), categories.get(2).getLineage());
        Assertions.assertEquals(checkCategoryList.get(2).getDepth(), categories.get(2).getDepth());
        Assertions.assertEquals(checkCategoryList.get(2).getCategories().size(), categories.get(2).getCategories().size());

        //Comparison of the fourth elements of collections
        Assertions.assertEquals(checkCategoryList.get(3).getId(), categories.get(3).getId());
        Assertions.assertEquals(checkCategoryList.get(3).getName(), categories.get(3).getName());
        Assertions.assertEquals(checkCategoryList.get(3).getLineage(), categories.get(3).getLineage());
        Assertions.assertEquals(checkCategoryList.get(3).getDepth(), categories.get(3).getDepth());
        Assertions.assertEquals(checkCategoryList.get(3).getCategories().size(), categories.get(3).getCategories().size());
    }

    @Test
    void testFindByDepth() {
        //Find categories from repository where depth = 2
        List<Category> categories = categoryRepo.findByDepth(2).orElseGet(Collections::emptyList);

        //Get categories from constructor where depth = 2
        List<Category> checkCategoryList = List.of(
                categoryList.get(1),
                categoryList.get(2),
                categoryList.get(3),
                categoryList.get(4)
        );

        //Check collections
        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(checkCategoryList.size(), categories.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkCategoryList.get(0).getId(), categories.get(0).getId());
        Assertions.assertEquals(checkCategoryList.get(0).getName(), categories.get(0).getName());
        Assertions.assertEquals(checkCategoryList.get(0).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(checkCategoryList.get(0).getDepth(), categories.get(0).getDepth());
        Assertions.assertEquals(checkCategoryList.get(0).getCategories().size(), categories.get(0).getCategories().size());

        //Comparison of the second elements of collections
        Assertions.assertEquals(checkCategoryList.get(1).getId(), categories.get(1).getId());
        Assertions.assertEquals(checkCategoryList.get(1).getName(), categories.get(1).getName());
        Assertions.assertEquals(checkCategoryList.get(1).getLineage(), categories.get(1).getLineage());
        Assertions.assertEquals(checkCategoryList.get(1).getDepth(), categories.get(1).getDepth());
        Assertions.assertEquals(checkCategoryList.get(1).getCategories().size(), categories.get(1).getCategories().size());

        //Comparison of the third elements of collections
        Assertions.assertEquals(checkCategoryList.get(2).getId(), categories.get(2).getId());
        Assertions.assertEquals(checkCategoryList.get(2).getName(), categories.get(2).getName());
        Assertions.assertEquals(checkCategoryList.get(2).getLineage(), categories.get(2).getLineage());
        Assertions.assertEquals(checkCategoryList.get(2).getDepth(), categories.get(2).getDepth());
        Assertions.assertEquals(checkCategoryList.get(2).getCategories().size(), categories.get(2).getCategories().size());

        //Comparison of the fourth elements of collections
        Assertions.assertEquals(checkCategoryList.get(3).getId(), categories.get(3).getId());
        Assertions.assertEquals(checkCategoryList.get(3).getName(), categories.get(3).getName());
        Assertions.assertEquals(checkCategoryList.get(3).getLineage(), categories.get(3).getLineage());
        Assertions.assertEquals(checkCategoryList.get(3).getDepth(), categories.get(3).getDepth());
        Assertions.assertEquals(checkCategoryList.get(3).getCategories().size(), categories.get(3).getCategories().size());


        //Find categories from repository where depth = 2
        categories = categoryRepo.findByDepth(1).orElseGet(Collections::emptyList);

        //Get categories from constructor where depth = 2
        checkCategoryList = List.of(categoryList.get(0));


        //Check collections
        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(checkCategoryList.size(), categories.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkCategoryList.get(0).getId(), categories.get(0).getId());
        Assertions.assertEquals(checkCategoryList.get(0).getName(), categories.get(0).getName());
        Assertions.assertEquals(checkCategoryList.get(0).getLineage(), categories.get(0).getLineage());
        Assertions.assertEquals(checkCategoryList.get(0).getDepth(), categories.get(0).getDepth());
        Assertions.assertEquals(checkCategoryList.get(0).getCategories().size(), categories.get(0).getCategories().size());
    }

    @Test
    void testFindByIdWithProducts() {
        //Find category with products from repository where id = 2
        Category category = categoryRepo.findByIdWithProducts(2L).orElseGet(Category::new);

        //Check category
        Assertions.assertEquals(2, category.getId());
        Assertions.assertEquals("Смартфоны", category.getName());
        Assertions.assertEquals(1, category.getLineage());
        Assertions.assertEquals(2, category.getDepth());

        //Check product collections
        Assertions.assertEquals(2, category.getProducts().size());
        Assertions.assertEquals(1, category.getProducts().iterator().next().getId());
        Assertions.assertEquals(412235, category.getProducts().iterator().next().getArticle());
    }
}