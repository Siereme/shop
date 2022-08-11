package app.model.product;

import app.model.category.Category;
import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
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
    private Set<ProductOption> options = new HashSet<>();

    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false)}
    )
    private Set<Category> categories = new HashSet<>();

}
