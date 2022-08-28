package app.model.category;

import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "category")
public class Category implements ICategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "image_link")
    private String imageLink;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();

    @Column(name = "lineage")
    private Long lineage;

    @Column(name = "depth")
    private int depth;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "category_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false)}
    )
    private Set<Product> products = new HashSet<>();


    @PreRemove
    public void removeDependencies(){
        Set.copyOf(products).forEach(this::removeProduct);
    }


    public void removeProduct(Product product){
        products.remove(product);
        product.getCategories().remove(this);
    }

}
