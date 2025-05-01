package com.techfira.eCommerce.service;



import com.techfira.eCommerce.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getOrdersByUserId(Integer userId);
    void deleteOrder(Long orderId);
}
