package com.shop.orderserver.utils.constant;

public interface ServiceUrl {

    String CUSTOMER_EXIST = "http://customer-server:8080/api/v1/customer/exist/";
    String CART_CLEAR = "http://shopping-cart-server:8080/api/v1/shopping-cart/clear/id/";

    String CATALOG_PRODUCT_SKUS = "http://product-server:8080/api/v1/product-catalog/product/skus";
    String CATALOG_PRODUCT_EXISTS = "http://product-server:8080/api/v1/product-catalog/product/exists";

}
