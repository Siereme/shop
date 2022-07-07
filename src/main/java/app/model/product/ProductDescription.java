package app.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_description")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductDescription {

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
