package com.techfira.eCommerce.service;

import com.techfira.eCommerce.dto.CartItemDto;

import java.util.List;

public interface CartItemService {
    CartItemDto addCartItem(CartItemDto cartItemDto);
    CartItemDto updateCartItem(Long cartItemId, CartItemDto cartItemDto);
    void deleteCartItem(Long cartItemId);
    CartItemDto getCartItemById(Long cartItemId);
    List<CartItemDto> getCartItemsByUserId(Integer userId);
}

