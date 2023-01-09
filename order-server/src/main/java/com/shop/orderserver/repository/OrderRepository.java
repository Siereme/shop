package com.shop.orderserver.repository;

import com.shop.orderserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select distinct o from Order o " +
            "left join fetch o.receiptDetail rd " +
            "left join fetch rd.receipt " +
            "left join fetch o.payment " +
            "left join fetch o.status")
    List<Order> findAll();

    @Query(value = "select distinct o from Order o " +
            "left join fetch o.receiptDetail rd " +
            "left join fetch rd.receipt " +
            "left join fetch o.payment " +
            "left join fetch o.status " +
            "where o.customerId = :id")
    List<Order> findAllByUserId(Long id);
}
