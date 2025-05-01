package com.techfira.eCommerce.service;


import com.techfira.eCommerce.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto addOrderItem(OrderItemDto orderItemDto);
    OrderItemDto updateOrderItem(Long orderItemId, OrderItemDto orderItemDto);
    OrderItemDto getOrderItemById(Long orderItemId);
    List<OrderItemDto> getOrderItemsByOrderId(Long orderId);
    void deleteOrderItem(Long orderItemId);
}

