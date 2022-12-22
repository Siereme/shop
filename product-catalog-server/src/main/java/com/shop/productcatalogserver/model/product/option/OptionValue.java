package com.shop.productcatalogserver.model.product.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shop.productcatalogserver.model.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Indexed
@Entity
@Table(name = "product_option_value")
public class OptionValue implements IOptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    @JsonIgnore
    private Long id;

    @JsonIgnoreProperties(value = "values")
    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Option option;

    @KeywordField
    @Column(name = "value")
    private String value;

    @JsonIgnore
    @ManyToMany(mappedBy = "options")
    private Set<Product> products = new HashSet<>();

    public OptionValue(Option option, String value) {
        this.option = option;
        this.value = value;
    }
}
