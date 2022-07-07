package app.model.dto.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterOption {

    private String key;
    private List<String> values;

    public FilterOption(String key, List<String> values) {
        this.key = key;
        this.values = values;
    }
}
