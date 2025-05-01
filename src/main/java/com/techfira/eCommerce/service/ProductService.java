package com.techfira.eCommerce.service;



import com.techfira.eCommerce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto product);
    ProductDto updateProduct(ProductDto product, Long productId);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
    void deleteProduct(Long productId);
}
