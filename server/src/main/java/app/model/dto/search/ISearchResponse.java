package app.model.dto.search;

import app.model.product.Product;

import java.util.List;

public interface ISearchResponse {

    List<Product> getProducts();

    PriceRangeDTO getPriceRange();

    List<OptionDTO> getOptions();

    void setProducts(List<Product> products);

    void setPriceRange(PriceRangeDTO priceRange);

    void setOptions(List<OptionDTO> options);
}
