package app.repository.category;

import app.model.category.Category;
import app.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select distinct c from Category c where c.parent = null")
    Set<Category> findAllFirstLevel();

    @Query(value = "select distinct c from Category c where c.lineage = :lineage and c.depth = :depth")
    Set<Category> findByLineageAndDepth(Long lineage, int depth);

    @Query(value = "select distinct c from Category c where c.depth = :depth")
    Set<Category> findByDepth(int depth);

}
