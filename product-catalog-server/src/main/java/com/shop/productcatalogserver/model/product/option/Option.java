package com.shop.productcatalogserver.model.product.option;

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
@Table(name = "product_option")
public class Option implements IOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @KeywordField
    @Column(name = "type")
    private String type;

    @JsonIgnoreProperties(value = "option")
    @OneToMany(
            mappedBy = "option",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OptionValue> values = new ArrayList<>();
}
