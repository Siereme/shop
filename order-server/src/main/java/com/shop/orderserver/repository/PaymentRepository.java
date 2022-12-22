package com.shop.orderserver.repository;

import com.shop.orderserver.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
