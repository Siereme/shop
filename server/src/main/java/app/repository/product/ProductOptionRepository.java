package app.repository.product;

import app.model.product.option.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    @Query(value = "select distinct o from ProductOption o where o.name = :name and o.value = :value")
    Optional<ProductOption> findByNameAndValue(String name, String value);
}
