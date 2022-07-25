package app.model.dto.category;

import app.model.category.ICategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDTO implements ICategory {
    private Long id;
    private String name;
    private String imageLink;
    private Long parentId;
    private List<Long> subCategoriesIds;
}
