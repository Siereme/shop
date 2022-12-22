package com.shop.productcatalogserver.repository.category;

import com.shop.productcatalogserver.dto.category.ICategoryDTO;
import com.shop.productcatalogserver.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select distinct c from Category c left join fetch c.categories")
    List<Category> findAll();


    @Query(value = "select distinct c from Category c " +
            "left join fetch c.categories " +
            "where c.id = :id")
    Optional<Category> findById(Long id);

    @Query(value = "select distinct c from Category c where c.id = :id")
    Optional<ICategoryDTO> findByIdWithoutSubcategories(Long id);

    @Query(value = "select distinct c from Category c where c.path = :path")
    Optional<ICategoryDTO> findByPathWithoutSubcategories(String path);

    @Query(value = "select distinct c from Category c " +
            "left join fetch c.categories " +
            "where c.path = :path")
    Optional<Category> findByPath(String path);

    @Query(value = "select distinct c from Category c " +
            "left join fetch c.categories " +
            "where c.depth = :depth")
    Optional<List<Category>> findByDepth(int depth);

    @Query(value = "select distinct c from Category c " +
            "left join fetch c.categories " +
            "left join fetch c.products p " +
            "left join fetch p.categories " +
            "where c.id = :id")
    Optional<Category> findByIdWithProducts(Long id);

}
