package app.model.dto.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchDTO {

    String query;
    Map<String, Set<String>> options;
    int page = 1;
    int pageSize = 24;

}
