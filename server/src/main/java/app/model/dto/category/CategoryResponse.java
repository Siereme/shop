package app.model.dto.category;

import app.model.category.Category;
import app.model.dto.product.IProductDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryResponse {

    private ICategoryDTO category;
    private Category parentCategory;
    private List<IProductDTO> products;

}
