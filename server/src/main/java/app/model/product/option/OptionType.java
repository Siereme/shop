package app.model.product.option;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_option_type")
public class OptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @KeywordField
    @Column(name = "type")
    private String type;

    @JsonIgnoreProperties(value = "optionType")
    @OneToMany(
            mappedBy = "optionType",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Option> options = new ArrayList<>();
}
