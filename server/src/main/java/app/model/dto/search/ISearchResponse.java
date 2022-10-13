package app.model.dto.search;

import app.model.product.Product;

import java.util.List;

public interface ISearchResponse {

    List<Product> getProducts();

    RangePriceDTO getRangePrice();

    List<OptionDTO> getOptions();

    long getPage();

    long getPagesCount();

    void setProducts(List<Product> products);

    void setRangePrice(RangePriceDTO rangePrice);

    void setOptions(List<OptionDTO> options);

    void setPage(long page);

    void setPagesCount(long pagesCount);

}
