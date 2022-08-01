package app.service.category;

import app.model.category.ICategory;
import app.model.dto.category.CategoryDTO;

public interface ICategoryService<T extends ICategory> {

    T addCategory(CategoryDTO categoryDTO);

}
