package app.service.product;

import app.consrtructor.TestCategoryConstructor;
import app.consrtructor.TestProductConstructor;
import app.model.category.Category;
import app.model.dto.product.ProductDTO;
import app.model.product.Product;
import app.model.product.option.ProductOption;
import app.repository.category.CategoryRepository;
import app.repository.product.ProductOptionRepository;
import app.repository.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepo;
    @Mock
    private ProductOptionRepository optionRepo;
    @Mock
    private CategoryRepository categoryRepo;

    private final TestProductConstructor productConstructor = new TestProductConstructor();
    private final TestCategoryConstructor categoryConstructor = new TestCategoryConstructor();

    @Test
    void addProduct() {
        //Prepare objects
        Product productMock = productConstructor.getById(5L);
        productMock.setId(101L);
        List<Category> categories = List.copyOf(productMock.getCategories());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setArticle(productMock.getArticle());
        productDTO.setName(productMock.getName());
        productDTO.setPrice(productMock.getPrice());
        productDTO.setImageLink(productMock.getImageLink());
        productDTO.setOptions(productMock.getOptions());
        Set<Long> ids = categories.stream().map(Category::getId).collect(Collectors.toSet());
        productDTO.setCategoriesIds(ids);

        ProductOption option = productDTO.getOptions().iterator().next();
        Optional<ProductOption> productOptionOptional = Optional.of(option);
        String optionName = option.getName();
        String optionValue = option.getValue();

        //When stubs are called
        Mockito.when(optionRepo.findByNameAndValue(optionName, optionValue)).thenReturn(productOptionOptional);
        Mockito.when(categoryRepo.findAllById(productDTO.getCategoriesIds())).thenReturn(categories);
        Mockito.when(productRepo.save(any(Product.class))).thenReturn(productMock);

        //Call a real service method
        Product product = productService.addProduct(productDTO);

        //Verify stub calls
        Mockito.verify(optionRepo, Mockito.times(1)).findByNameAndValue(optionName, optionValue);
        Mockito.verify(categoryRepo, Mockito.times(1)).findAllById(productDTO.getCategoriesIds());
        Mockito.verify(productRepo, Mockito.times(1)).save(any(Product.class));

        //Check the resulting object
        Assertions.assertEquals(productMock.getId(), product.getId());
        Assertions.assertEquals(productMock.getArticle(), product.getArticle());
        Assertions.assertEquals(productMock.getName(), product.getName());
        Assertions.assertEquals(productMock.getOptions().size(), product.getOptions().size());
        Assertions.assertEquals(categories.size(), product.getCategories().size());
    }

    @Test
    void deleteProduct() {
        //Prepare objects
        Product productMock = productConstructor.getById(5L);
        Optional<Product> productOptional = Optional.of(productMock);
        Optional<Integer> countOptional = Optional.of(0);

        //When stubs are called
        Mockito.when(productRepo.findByIdWithCategoryProducts(5L)).thenReturn(productOptional);
        Mockito.when(productRepo.findCountOrderItems(5L)).thenReturn(countOptional);
        Mockito.doNothing().when(productRepo).delete(productMock);

        //Call a real service method
        productService.deleteById(5L);

        //Verify stub calls
        Mockito.verify(productRepo, Mockito.times(1)).findByIdWithCategoryProducts(5L);
        Mockito.verify(productRepo, Mockito.times(1)).findCountOrderItems(5L);
        Mockito.verify(productRepo, Mockito.times(1)).delete(productMock);
    }

    @Test
    void getPopular() {
        //Prepare objects
        List<Product> productsMock = productConstructor.getAll();
        List<Long> ids = productsMock.stream().map(Product::getId).collect(Collectors.toList());
        Optional<List<Long>> idsOptional = Optional.of(ids);

        //When stubs are called
        Mockito.when(productRepo.findPopularIds(PageRequest.of(0, 10))).thenReturn(idsOptional);
        Mockito.when(productRepo.findAllById(ids)).thenReturn(productsMock);

        //Call a real service method
        List<Product> products = productService.getPopular();

        //Verify stub calls
        Mockito.verify(productRepo, Mockito.times(1)).findPopularIds(PageRequest.of(0, 10));
        Mockito.verify(productRepo, Mockito.times(1)).findAllById(ids);

        //Check the resulting object
        Assertions.assertEquals(productsMock.size(), products.size());
        Assertions.assertEquals(productsMock.get(0).getArticle(), products.get(0).getArticle());
    }

    @Test
    void findByCategoryId() {
        //Prepare objects
        List<Product> productsMock = productConstructor.getByCategoryId(2L);
        Category categoryMock = categoryConstructor.getById(2L);
        Optional<Category> categoryOptional = Optional.of(categoryMock);
        Optional<List<Product>> optionalProducts = Optional.of(productsMock);

        //When stubs are called
        Mockito.when(categoryRepo.findById(2L)).thenReturn(categoryOptional);
        Mockito.when(productRepo.findByPathInDepth("1/2/")).thenReturn(optionalProducts);

        //Call a real service method
        List<Product> products = productService.findByCategoryId(2L);

        //Verify stub calls
        Mockito.verify(categoryRepo, Mockito.times(1)).findById(2L);
        Mockito.verify(productRepo, Mockito.times(1)).findByPathInDepth("1/2/");

        //Check the resulting object
        Assertions.assertEquals(productsMock.size(), products.size());
        Assertions.assertEquals(productsMock.get(0).getArticle(), products.get(0).getArticle());
    }
}