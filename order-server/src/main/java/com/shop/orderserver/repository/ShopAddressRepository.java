package com.shop.orderserver.repository;

import com.shop.orderserver.model.address.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAddressRepository extends JpaRepository<ShopAddress, Long> {
}
