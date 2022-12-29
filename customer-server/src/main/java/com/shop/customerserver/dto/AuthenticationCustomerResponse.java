package com.shop.customerserver.dto;

import com.shop.customerserver.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationCustomerResponse {

    private String accessToken;
    private String refreshToken;
    private Customer user;

}
