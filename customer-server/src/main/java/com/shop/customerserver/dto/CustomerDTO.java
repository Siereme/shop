package com.shop.customerserver.dto;

import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements ICustomerDTO {

    private Long id;
    private String name = UUID.randomUUID().toString();
    private String surname = UUID.randomUUID().toString();
    private String patronymic = UUID.randomUUID().toString();
    private String email = UUID.randomUUID().toString();
    private String password = UUID.randomUUID().toString();
    private String phone = UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    private CustomerRole role;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

}
