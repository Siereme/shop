package com.shop.orderserver.model.userDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
public class UserDetails implements IOrderUserDetails {

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String phone;

}
