package app.model.dto.search;

import app.model.category.Category;
import app.model.dto.category.ICategoryDTO;
import app.model.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchCategoryResponse implements ISearchResponse{

    ICategoryDTO category;
    Category parentCategory;
    List<Product> products;
    PriceRangeDTO priceRange;
    Map<String, Set<String>> options;

}
