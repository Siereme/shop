package app.model.product.option;

import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "options")
@Entity
@Table(name = "product_option")
public class ProductOption implements IOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    @JsonIgnore
    private Long id;

    @KeywordField
    @Column(name = "name")
    private String name;

    @KeywordField
    @Column(name = "value")
    private String value;

    @JsonIgnore
    @ManyToMany(mappedBy = "options")
    private Set<Product> products = new HashSet<>();

    public ProductOption(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
