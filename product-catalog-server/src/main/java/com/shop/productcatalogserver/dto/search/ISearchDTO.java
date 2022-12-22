package com.shop.productcatalogserver.dto.search;

import java.util.List;

public interface ISearchDTO {

    RangePriceDTO getRangePrice();

    List<OptionDTO> getOptions();

    int getPage();

    int getPageSize();

    int getShift();

    void setRangePrice(RangePriceDTO rangePrice);

    void setOptions(List<OptionDTO> options);

    void setPage(int page);

    void setPageSize(int pageSize);

    void setShift(int shift);

}
