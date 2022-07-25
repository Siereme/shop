package app.utils.specification;

import app.model.dto.filter.Filter;
import app.model.dto.filter.FilterOption;
import app.model.product.Product;
import app.model.product.ProductOption;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private Filter filter;

    public ProductSpecification(Filter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Product, ProductOption> join = root.join(filter.getName(), JoinType.LEFT);
        root.fetch("categories");
        root.fetch("description");
        root.fetch("options");

        FilterOption option = filter.getOptions().get(0);
        Predicate predicateType = criteriaBuilder.equal(join.get("name"), option.getKey());
        Predicate predicateValues = join.get("value").in(option.getValues());
        Predicate predicate = criteriaBuilder.and(predicateType, predicateValues);

        for (int i = 1; i < filter.getOptions().size(); i++) {
            option = filter.getOptions().get(i);

            predicateType = criteriaBuilder.equal(join.get("name"), option.getKey());
            predicateValues = join.get("value").in(option.getValues());

            Predicate newPredicate = criteriaBuilder.and(predicateType, predicateValues);
            predicate = criteriaBuilder.and(predicate, newPredicate);
        }
        return predicate;
    }
}
