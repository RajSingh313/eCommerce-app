package com.techfira.eCommerce.serviceImpl;

import com.techfira.eCommerce.dto.OrderItemDto;
import com.techfira.eCommerce.entity.Order;
import com.techfira.eCommerce.entity.OrderItem;
import com.techfira.eCommerce.entity.Product;
import com.techfira.eCommerce.exceptions.ResourceNotFoundException;
import com.techfira.eCommerce.repository.OrderItemRepository;
import com.techfira.eCommerce.repository.OrderRepository;
import com.techfira.eCommerce.repository.ProductRepository;
import com.techfira.eCommerce.service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderItemDto addOrderItem(OrderItemDto orderItemDto) {
        Order order = orderRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderItemDto.getOrderId()));

        Product product = productRepository.findById(orderItemDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderItemDto.getProductId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPriceAtTime(product.getPrice());  // Assuming price at time is current price of product

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return modelMapper.map(savedOrderItem, OrderItemDto.class);
    }

    @Override
    public OrderItemDto updateOrderItem(Long orderItemId, OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", orderItemId));

        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPriceAtTime(orderItemDto.getPriceAtTime());

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        return modelMapper.map(updatedOrderItem, OrderItemDto.class);
    }

    @Override
    public OrderItemDto getOrderItemById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", orderItemId));

        return modelMapper.map(orderItem, OrderItemDto.class);
    }

    @Override
    public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
        return orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", orderItemId));

        orderItemRepository.delete(orderItem);
    }
}

