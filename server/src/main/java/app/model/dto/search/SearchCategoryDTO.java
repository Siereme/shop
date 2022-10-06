package app.model.dto.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchCategoryDTO {

    String path;
    int depth = 1;
    Map<String, Set<String>> options;
    int page = 1;
    int pageSize = 24;

}
