package com.techfira.eCommerce.service;
import com.techfira.eCommerce.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category, Long categoryId);
    CategoryDto getCategoryById(Long categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Long categoryId);
}

