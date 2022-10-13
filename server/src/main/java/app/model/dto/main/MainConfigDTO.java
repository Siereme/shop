package app.model.dto.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MainConfigDTO {

    private Long userId;
    private boolean withCategories;
    private int categoryLevel;
    private boolean withShoppingCart;
    private boolean withOrders;
    private boolean withProductsPopular;

}
