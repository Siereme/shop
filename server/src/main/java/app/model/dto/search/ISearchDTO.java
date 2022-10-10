package app.model.dto.search;

import java.util.List;

public interface ISearchDTO {
    RangePriceDTO getRangePrice();

    List<OptionDTO> getOptions();

    int getPage();

    int getPageSize();

    void setRangePrice(RangePriceDTO rangePrice);

    void setOptions(List<OptionDTO> options);

    void setPage(int page);

    void setPageSize(int pageSize);
}
