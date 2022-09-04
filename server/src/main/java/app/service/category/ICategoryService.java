package app.service.category;

import app.model.category.ICategory;
import app.model.dto.category.CategoryRequest;

public interface ICategoryService<T extends ICategory> {

    T addCategory(CategoryRequest categoryRequest);

    T getByPathAndDepth(String path, int depth);

}
