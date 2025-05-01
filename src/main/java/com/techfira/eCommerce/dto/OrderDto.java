package com.techfira.eCommerce.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private Integer userId;
    private LocalDateTime createdAt;
    private String status;
    private List<OrderItemDto> orderItems;
}

