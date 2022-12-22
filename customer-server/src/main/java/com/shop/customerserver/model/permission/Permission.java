package com.shop.customerserver.model.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Getter
@Setter
@RequiredArgsConstructor
public class Permission implements IPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "name")
    private String name;

}
