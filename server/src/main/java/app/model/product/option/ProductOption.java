package app.model.product.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_option")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductOption implements IOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

}
