package app.model.dto.search;

import app.model.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchResponse implements ISearchResponse {

    List<Product> products;
    PriceRangeDTO priceRange;
    List<OptionDTO> options;

}
