package com.shop.customerserver.repository;

import com.shop.customerserver.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select distinct r from Role r where r.name = :name")
    Optional<Role> findByName(String name);

    @Query(value = "select distinct r from Role r " +
            "left join fetch r.permissions " +
            "left join fetch r.customers " +
            "where r.name = :name")
    Optional<Role> findByNameWithPermissions(String name);
}
