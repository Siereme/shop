package app.constructor.search;

import app.model.dto.search.ISearchResponse;
import app.model.dto.search.OptionDTO;
import app.model.product.Product;

import java.util.List;

public interface ISearchResponseBuilder {
    ISearchResponseBuilder create(ISearchResponse response);

    ISearchResponseBuilder setProducts(List<Product> products);

    ISearchResponseBuilder setPriceRange();

    ISearchResponseBuilder setCheckedOptions(List<OptionDTO> options);

    ISearchResponseBuilder setOptions();

    ISearchResponse build();
}
