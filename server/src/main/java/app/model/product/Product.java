package app.model.product;

import app.model.category.Category;
import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "products")
@Table(name = "product")
public class Product implements IProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "article_id")
    private Long article;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_link")
    private String imageLink;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "shortDescription", column = @Column(name = "short_description")),
            @AttributeOverride(name = "longDescription", column = @Column(name = "long_description")),
    })
    private ProductDescription description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_attribute_option",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "option_id", nullable = false)}
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "options")
    private Set<ProductOption> options = new HashSet<>();

    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "categories")
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

    public void removeOption(ProductOption option) {
        options.remove(option);
        option.getProducts().remove(this);
    }

}
