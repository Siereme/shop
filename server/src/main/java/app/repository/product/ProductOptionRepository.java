package app.repository.product;

import app.model.product.option.CheckedUserOption;
import app.model.product.option.Option;
import app.model.product.option.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<Option, Long> {

    @Query(value = "select distinct o from OptionValue o where o.option = :type and o.value = :value")
    Optional<OptionValue> findOptionValueByTypeAndValue(Option type, String value);

    @Query(value = "select distinct o from Option o " +
            "left join fetch o.values " +
            "where o.id in :ids")
    Optional<List<Option>> findAllOptionByIds(List<Long> ids);

    @Query(value = "select distinct c from CheckedUserOption c where c.userId = :userId")
    Optional<CheckedUserOption> findCheckedOptionsByUserId(Long userId);

}
