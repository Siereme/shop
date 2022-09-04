package app.model.dto.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryConfigDTO {
    private Long id;
    private boolean withParent;
    private boolean withProducts;
}
