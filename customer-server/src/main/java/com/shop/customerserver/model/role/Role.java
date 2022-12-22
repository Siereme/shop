package com.shop.customerserver.model.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.permission.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "role")
public class Role implements IRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", nullable = false)}
    )
    Set<Permission> permissions;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.MERGE)
    private Set<Customer> customers;

}
