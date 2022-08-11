package app.model.dto.product;

import app.model.product.description.ProductDescription;
import app.model.product.option.ProductOption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDTO {

    private Long article;
    private String name;
    private Double price;
    private String imageLink;
    private ProductDescription description;
    private Set<ProductOption> options = new HashSet<>();
    private Set<Long> categoriesIds = new HashSet<>();
    private int count;

}
