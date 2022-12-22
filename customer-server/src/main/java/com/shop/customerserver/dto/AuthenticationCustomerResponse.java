package com.shop.customerserver.dto;

import com.shop.customerserver.model.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationCustomerResponse {

    private String accessToken;
    private String refreshToken;
    private Customer user;

    public AuthenticationCustomerResponse() {
    }

    public AuthenticationCustomerResponse(String accessToken, String refreshToken, Customer user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

}
