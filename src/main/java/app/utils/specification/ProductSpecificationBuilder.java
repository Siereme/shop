package app.utils.specification;

import app.model.dto.filter.Filter;
import app.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductSpecificationBuilder {
    List<Filter> params;

    public ProductSpecificationBuilder(List<Filter> paramsList) {
        this.params = paramsList;
    }

    public Specification<Product> build() {
        List<Specification<Product>> specifications = params.stream().map(ProductSpecification::new).collect(Collectors.toList());

        Specification specification = specifications.get(0);

        for (int i = 1; i < specifications.size(); i++){
            specification = Specification.where(specification).and(specifications.get(i));
        }

        return specification;
    }
}
