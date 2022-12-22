package com.shop.customerserver.repository;

import com.shop.customerserver.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select distinct c from Customer c " +
            "left join fetch c.role r " +
            "left join fetch r.permissions")
    List<Customer> findAll();

    @Query(value = "select distinct c from Customer c " +
            "left join fetch c.role r left join fetch r.permissions " +
            "where c.id = :id")
    Optional<Customer> findById(Long id);

    @Query(value = "select distinct c from Customer c " +
            "left join fetch c.role r left join fetch r.permissions " +
            "where c.email = :email")
    Optional<Customer> findByEmail(String email);

    @Query(value = "select distinct c from Customer c " +
            "left join fetch c.role r left join fetch r.permissions " +
            "where c.phone = :phone")
    Optional<Customer> findByPhone(String phone);


    @Query(value = "select count(c) from Customer c where c.id = :id")
    Long findCountById(Long id);

}
