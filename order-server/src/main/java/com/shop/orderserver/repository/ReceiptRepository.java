package com.shop.orderserver.repository;

import com.shop.orderserver.model.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
