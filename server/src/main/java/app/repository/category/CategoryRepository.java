package app.repository.category;

import app.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select distinct c from Category c where c.parent = null")
    Optional<List<Category>> findAllFirstLevel();

    @Query(value = "select distinct c from Category c where c.lineage = :lineage and c.depth = :depth")
    Optional<List<Category>> findByLineageAndDepth(Long lineage, int depth);

    @Query(value = "select distinct c from Category c where c.depth = :depth")
    Optional<List<Category>> findByDepth(int depth);

}
