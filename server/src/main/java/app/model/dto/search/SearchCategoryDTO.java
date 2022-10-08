package app.model.dto.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchCategoryDTO implements ISearchDTO {

    CategoryDTO category;
    PriceRangeDTO priceRange;
    List<OptionDTO> options;
    int page = 1;
    int pageSize = 24;

}