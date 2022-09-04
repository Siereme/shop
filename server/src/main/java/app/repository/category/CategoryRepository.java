package app.repository.category;

import app.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    @Query(value = "select distinct c from Category c left join fetch c.categories")
    List<Category> findAll();

    @Override
    @Query(value = "select distinct c from Category c " +
            "left join fetch c.categories " +
            "where c.id = :id")
    Optional<Category> findById(Long id);

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
