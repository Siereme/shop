package app.model.product.option;

import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

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
public class Option implements IOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    @JsonIgnore
    private Long id;

    @JsonIgnoreProperties(value = "options")
    @IndexedEmbedded(structure = ObjectStructure.NESTED)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private OptionType optionType;

    @FullTextField
    @Column(name = "value")
    private String value;

    @JsonIgnore
    @ManyToMany(mappedBy = "options")
    private Set<Product> products = new HashSet<>();

    public Option(OptionType optionType, String value) {
        this.optionType = optionType;
        this.value = value;
    }
}
