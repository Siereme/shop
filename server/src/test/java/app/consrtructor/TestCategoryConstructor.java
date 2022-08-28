package app.consrtructor;

import app.model.category.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestCategoryConstructor {

    private final List<Category> categoryList = new ArrayList<>();

    public TestCategoryConstructor() {
        construct();
    }

    private void construct() {
        Category parentCategory = new Category();
        parentCategory.setId(1L);
        parentCategory.setName("Смартфоны и гаджеты");
        parentCategory.setLineage(1L);
        parentCategory.setDepth(1);

        Category subCategory1 = new Category();
        subCategory1.setId(2L);
        subCategory1.setName("Смартфоны");
        subCategory1.setLineage(1L);
        subCategory1.setDepth(2);
        subCategory1.setParent(parentCategory);
        parentCategory.getCategories().add(subCategory1);

        Category subCategory2 = new Category();
        subCategory2.setId(3L);
        subCategory2.setName("Планшеты");
        subCategory2.setLineage(1L);
        subCategory2.setDepth(2);
        subCategory2.setParent(parentCategory);
        parentCategory.getCategories().add(subCategory2);

        Category subCategory3 = new Category();
        subCategory3.setId(4L);
        subCategory3.setName("Умные часы и браслеты");
        subCategory3.setLineage(1L);
        subCategory3.setDepth(2);
        subCategory3.setParent(parentCategory);
        parentCategory.getCategories().add(subCategory3);

        Category subCategory4 = new Category();
        subCategory4.setId(5L);
        subCategory4.setName("Электронные книги");
        subCategory4.setLineage(1L);
        subCategory4.setDepth(2);
        subCategory4.setParent(parentCategory);
        parentCategory.getCategories().add(subCategory4);

        categoryList.addAll(List.of(parentCategory, subCategory1, subCategory2, subCategory3, subCategory4));
    }

    public List<Category> getAll() {
        return categoryList;
    }

    public List<Category> getFirstLevelCategories() {
        return categoryList
                .stream()
                .filter(category -> category.getParent() == null)
                .collect(Collectors.toList());
    }

    public List<Category> getCategoriesByDepth(long depth) {
        return categoryList
                .stream()
                .filter(category -> category.getDepth() == depth)
                .collect(Collectors.toList());
    }

    public Category getById(Long id){
        return categoryList
                .stream()
                .filter(category -> Objects.equals(category.getId(), id))
                .findFirst().orElseGet(Category::new);
    }

}
