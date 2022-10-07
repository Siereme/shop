package app.model.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class OptionValueDTO implements Serializable {

    String value;
    boolean checked;

}
