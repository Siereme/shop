package app.model.product.description;

import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_description")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductDescription implements IDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "description_id")
    @JsonIgnore
    private Long id;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToOne(mappedBy = "description")
    private Product product;
}
