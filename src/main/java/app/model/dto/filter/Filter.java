package app.model.dto.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Filter {

    private String name;
    private List<FilterOption> options;

    public Filter(String key, List<FilterOption> options){
        this.name = key;
        this.options = options;
    }
}
