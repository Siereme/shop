package com.shop.authenticationserver.repository;

import com.shop.authenticationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
