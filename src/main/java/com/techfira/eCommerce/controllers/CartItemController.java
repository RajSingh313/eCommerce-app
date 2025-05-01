package com.techfira.eCommerce.controllers;

import com.techfira.eCommerce.dto.CartItemDto;
import com.techfira.eCommerce.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Add to cart
    @PostMapping("/")
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemDto cartItemDto) {
        return new ResponseEntity<>(cartItemService.addCartItem(cartItemDto), HttpStatus.CREATED);
    }

    // Update cart item quantity
    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long cartItemId, @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartItemService.updateCartItem(cartItemId, cartItemDto));
    }

    // Delete cart item
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Map<String, Object>> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cart item deleted successfully");
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

    // Get single cart item
    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Long cartItemId) {
        return ResponseEntity.ok(cartItemService.getCartItemById(cartItemId));
    }

    // Get all cart items for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartItemsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartItemService.getCartItemsByUserId(userId));
    }
}
