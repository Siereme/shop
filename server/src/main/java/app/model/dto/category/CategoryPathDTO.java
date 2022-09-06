package app.model.dto.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryPathDTO {
    private String path;
    private int depth;
}