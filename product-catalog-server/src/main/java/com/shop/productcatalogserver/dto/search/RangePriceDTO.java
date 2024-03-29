package com.shop.productcatalogserver.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RangePriceDTO {

    double rangeMin;
    double rangeMax;

    double priceMin;
    double priceMax;

    public boolean isValid() {
        return rangeMin != rangeMax && priceMin != priceMax
                && (rangeMin != priceMin || rangeMax != priceMax);
    }

}
