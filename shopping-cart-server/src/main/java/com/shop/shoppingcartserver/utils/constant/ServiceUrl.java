package com.shop.shoppingcartserver.utils.constant;

public interface ServiceUrl {

    String CUSTOMER_EXIST = "http://customer-server:8080/api/v1/customer/exist/";
    String PRODUCT_SKU = "http://product-server:8080/api/v1/product-catalog/product/sku/";
    String CATALOG_PRODUCT_EXISTS = "http://product-server:8080/api/v1/product-catalog/product/exists";

}
