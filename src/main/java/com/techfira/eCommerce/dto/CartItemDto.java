package com.techfira.eCommerce.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemDto {
    private Long id;
    private Integer userId;
    private Long productId;
    private int quantity;
}
