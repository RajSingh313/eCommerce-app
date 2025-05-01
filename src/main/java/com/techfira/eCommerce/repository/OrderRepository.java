package com.techfira.eCommerce.repository;

import com.techfira.eCommerce.entity.Order;
import com.techfira.eCommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
