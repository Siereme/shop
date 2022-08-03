package app.model.dto.product;

import app.model.category.Category;
import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDTO {

    private Long productId;
    private String name;
    private Double price;
    private ProductDescription description;
    private Set<ProductOption> attributes = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
    private int count;

}
