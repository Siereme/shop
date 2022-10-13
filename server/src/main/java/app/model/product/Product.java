package app.model.product;

import app.model.category.Category;
import app.model.product.description.ProductDescription;
import app.model.product.option.OptionValue;
import app.search.OptionValueBridge;
import app.search.OptionValueConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Indexed
public class Product implements IProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "article_id")
    private Long article;

    @FullTextField
    @Column(name = "name")
    private String title;

    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES)
    @Column(name = "price")
    private Double price;

    @Column(name = "image_link")
    private String imageLink;

    @IndexedEmbedded
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "shortDescription", column = @Column(name = "short_description")),
            @AttributeOverride(name = "longDescription", column = @Column(name = "long_description")),
    })
    private ProductDescription description;

    @Convert(converter = OptionValueConverter.class)
    @KeywordField(
            name = "optionValue",
            valueBridge = @ValueBridgeRef(type = OptionValueBridge.class),
            projectable = Projectable.YES
    )
    @IndexedEmbedded
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_attribute_option",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "option_id", nullable = false)}
    )
    private Set<OptionValue> options = new HashSet<>();

    @IndexedEmbedded(structure = ObjectStructure.NESTED)
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();


    @PreRemove
    public void removeDependencies() {
        Set.copyOf(categories).forEach(this::removeCategory);
        Set.copyOf(options).forEach(this::removeOption);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getProducts().remove(this);
    }

    public void removeOption(OptionValue option) {
        options.remove(option);
        option.getProducts().remove(this);
    }

}
