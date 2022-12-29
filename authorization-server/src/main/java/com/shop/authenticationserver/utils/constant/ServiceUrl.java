package com.shop.authenticationserver.utils.constant;

public interface ServiceUrl {

    String CUSTOMER_GET_EMAIL = "http://customer-server:8080/api/v1/customer/user-details/email/";
    String CUSTOMER_ADD_ANONYMOUS = "http://customer-server:8080/api/v1/customer/user-details/anonymous";
    String CUSTOMER_UPDATE = "http://customer-server:8080/api/v1/customer/update";
    String CUSTOMER_ADD = "http://customer-server:8080/api/v1/customer/add";

}
