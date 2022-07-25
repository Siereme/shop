package app.model.category;

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
public class Category implements ICategory{

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
    public Category parent;

    @OneToMany(mappedBy="parent", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval=true)
    private Set<Category> categories = new HashSet<>();

    @Column(name = "lineage")
    private Long lineage;

    @Column(name = "depth")
    private int depth;
}
