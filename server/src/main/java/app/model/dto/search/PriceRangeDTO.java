package app.model.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PriceRangeDTO {

    double priceMin;
    double min;
    double priceMax;
    double max;

}
