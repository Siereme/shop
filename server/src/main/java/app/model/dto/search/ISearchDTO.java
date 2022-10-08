package app.model.dto.search;

public interface ISearchDTO {
    PriceRangeDTO getPriceRange();

    java.util.List<OptionDTO> getOptions();

    int getPage();

    int getPageSize();

    void setPriceRange(PriceRangeDTO priceRange);

    void setOptions(java.util.List<OptionDTO> options);

    void setPage(int page);

    void setPageSize(int pageSize);
}
