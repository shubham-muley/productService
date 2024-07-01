package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.UpdateCategoryRequestDto;
import dev.shubham.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryByTitle(String title);
    void deleteCategoryById(Long Id);

    int updateCategory(UpdateCategoryRequestDto updateCategoryRequestDto);

    Category findCategoryById(Long Id);
    List<Category> findAllCategories();
}
