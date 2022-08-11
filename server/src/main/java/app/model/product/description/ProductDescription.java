package app.model.product.description;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDescription implements IDescription {

    private String shortDescription;

    private String longDescription;

}
