package app.model.dto.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchCategoryDTO {

    String path;
    int depth = 1;
    List<OptionDTO> options;
    int page = 1;
    int pageSize = 24;

}
