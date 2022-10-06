package app.model.dto.search;

import app.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISearchResponse {

    List<Product> getProducts();

    PriceRangeDTO getPriceRange();

    Map<String, Set<String>> getOptions();

    void setProducts(List<Product> products);

    void setPriceRange(PriceRangeDTO priceRange);

    void setOptions(Map<String, Set<String>> options);
}
