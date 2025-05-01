package com.techfira.eCommerce.repository;

import com.techfira.eCommerce.entity.CartItem;
import com.techfira.eCommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}
