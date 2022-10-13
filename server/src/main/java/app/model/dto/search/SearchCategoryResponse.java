package app.model.dto.search;

import app.model.category.Category;
import app.model.dto.category.ICategoryDTO;
import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchCategoryResponse implements ISearchResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    ICategoryDTO category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Category parentCategory;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Product> products;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    RangePriceDTO rangePrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<OptionDTO> options;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    long page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    long pagesCount;

}
