package app.model.dto.category;

import app.model.category.Category;
import app.model.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryResponse {

    private Category category;
    private Category parentCategory;
    private List<Product> products;

}
