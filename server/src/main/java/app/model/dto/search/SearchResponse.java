package app.model.dto.search;

import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchResponse implements ISearchResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Product> products;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    PriceRangeDTO priceRange;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<OptionDTO> options;

}
