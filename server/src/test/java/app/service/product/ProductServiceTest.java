package app.service.product;

import app.consrtructor.TestProductConstructor;
import app.model.category.Category;
import app.model.dto.product.ProductDTO;
import app.model.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Mock
    private ProductService productService;
    private final TestProductConstructor constructor = new TestProductConstructor();

    @Test
    void addProduct() {
        Product productMock = constructor.getById(5L);
        productMock.setId(101L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setArticle(productMock.getArticle());
        productDTO.setName(productMock.getName());
        productDTO.setPrice(productMock.getPrice());
        productDTO.setImageLink(productMock.getImageLink());
        productDTO.setDescription(productMock.getDescription());
        productDTO.setOptions(productMock.getOptions());
        Set<Long> ids = productMock.getCategories().stream().map(Category::getId).collect(Collectors.toSet());
        productDTO.setCategoriesIds(ids);

        Mockito.when(productService.addProduct(productDTO)).thenReturn(productMock);

        Product product = productService.addProduct(productDTO);

        Assertions.assertEquals(productMock.getId(), product.getId());
        Assertions.assertEquals(productMock.getArticle(), product.getArticle());
        Assertions.assertEquals(productMock.getName(), product.getName());
        Assertions.assertEquals(productMock.getDescription(), product.getDescription());
        Assertions.assertEquals(productMock.getOptions().size(), product.getOptions().size());
        Assertions.assertEquals(productMock.getCategories().size(), product.getCategories().size());
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(5L);

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(5L);
    }

    @Test
    void getPopular() {
        List<Product> productsMock = constructor.getAll();

        Mockito.when(productService.getPopular()).thenReturn(productsMock);

        List<Product> products = productService.getPopular();

        Assertions.assertEquals(productsMock.size(), products.size());
        Assertions.assertEquals(productsMock.get(0).getArticle(), products.get(0).getArticle());
    }

    @Test
    void findByCategoryId() {
        List<Product> productsMock = constructor.getByCategoryId(2L);

        Mockito.when(productService.findByCategoryId(2L)).thenReturn(productsMock);

        List<Product> products = productService.findByCategoryId(2L);

        Assertions.assertEquals(productsMock.size(), products.size());
        Assertions.assertEquals(productsMock.get(0).getArticle(), products.get(0).getArticle());
    }
}