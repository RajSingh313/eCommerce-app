package com.techfira.eCommerce.serviceImpl;

import com.techfira.eCommerce.dto.CartItemDto;
import com.techfira.eCommerce.entity.CartItem;
import com.techfira.eCommerce.entity.Product;
import com.techfira.eCommerce.entity.User;
import com.techfira.eCommerce.exceptions.ResourceNotFoundException;
import com.techfira.eCommerce.repository.CartItemRepository;
import com.techfira.eCommerce.repository.ProductRepository;
import com.techfira.eCommerce.repository.UserRepository;
import com.techfira.eCommerce.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartItemDto addCartItem(CartItemDto cartItemDto) {
        User user = userRepo.findById(cartItemDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", cartItemDto.getUserId()));

        Product product = productRepo.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", cartItemDto.getProductId()));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDto.getQuantity());

        CartItem saved = cartItemRepo.save(cartItem);
        return modelMapper.map(saved, CartItemDto.class);
    }

    @Override
    public CartItemDto updateCartItem(Long cartItemId, CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "id", cartItemId));

        cartItem.setQuantity(cartItemDto.getQuantity());
        CartItem updated = cartItemRepo.save(cartItem);

        return modelMapper.map(updated, CartItemDto.class);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "id", cartItemId));
        cartItemRepo.delete(cartItem);
    }

    @Override
    public CartItemDto getCartItemById(Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "id", cartItemId));
        return modelMapper.map(cartItem, CartItemDto.class);
    }

    @Override
    public List<CartItemDto> getCartItemsByUserId(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<CartItem> items = cartItemRepo.findByUser(user);
        return items.stream()
                .map(item -> modelMapper.map(item, CartItemDto.class))
                .collect(Collectors.toList());
    }
}
