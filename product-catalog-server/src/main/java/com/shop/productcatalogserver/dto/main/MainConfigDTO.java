package com.shop.productcatalogserver.dto.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MainConfigDTO {

    private boolean withCategories;
    private int categoryLevel;
    private boolean withShoppingCart;
    private boolean withOrders;
    private boolean withProductsPopular;

}
