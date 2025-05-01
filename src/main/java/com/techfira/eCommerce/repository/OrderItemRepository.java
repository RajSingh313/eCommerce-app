package com.techfira.eCommerce.repository;

import com.techfira.eCommerce.entity.OrderItem;
import com.techfira.eCommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}

