package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.UpdateCategoryRequestDto;
import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("DbCategoryService")
public class DbCategoryService implements CategoryService{

    CategoryRepository categoryRepository;

    public DbCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByTitle(String title) {
        Optional<Category> category = categoryRepository.findByTitle(title);
        return category.orElse(null); //Controller will throw NullPointerException
    }


    @Override
    @Transactional
    public void deleteCategoryById(Long Id) {
        Category category = findCategoryById(Id);
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public int updateCategory(UpdateCategoryRequestDto updateCategoryRequestDto) {
        Category category = new Category();
        category.setTitle(updateCategoryRequestDto.getTitle());
        category.setId(updateCategoryRequestDto.getId());
        category.setUpdatedAt(new Date());
        return categoryRepository.updateCategory(category);
    }

    @Override
    public Category findCategoryById(Long Id) {
        Optional<Category> category = categoryRepository.findById(Id);
        return category.orElse(null); //Controller will throw NullPointerException
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
