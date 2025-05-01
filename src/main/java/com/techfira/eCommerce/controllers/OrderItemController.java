package com.techfira.eCommerce.controllers;

import com.techfira.eCommerce.dto.OrderItemDto;
import com.techfira.eCommerce.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Add Order Item
    @PostMapping("/")
    public ResponseEntity<OrderItemDto> addOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItemDto createdOrderItem = orderItemService.addOrderItem(orderItemDto);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    // Get Order Item by ID
    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long orderItemId) {
        OrderItemDto orderItem = orderItemService.getOrderItemById(orderItemId);
        return ResponseEntity.ok(orderItem);
    }

    // Get All Order Items for a Specific Order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItemDto> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }

    // Update Order Item
    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable Long orderItemId, @RequestBody OrderItemDto orderItemDto) {
        OrderItemDto updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemDto);
        return ResponseEntity.ok(updatedOrderItem);
    }

    // Delete Order Item by ID
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Map<String, Object>> deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order Item deleted successfully");
        response.put("success", true);
        return ResponseEntity.ok(response);
    }
}

