package dev.shubham.productservice.controllers;

import dev.shubham.productservice.dtos.UpdateCategoryRequestDto;
import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class categoryController {

    CategoryService categoryService;

    public categoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/category/title/{categoryTitle}")
    public ResponseEntity<Category> getCategoryByTitle(@PathVariable String categoryTitle) {
        return new ResponseEntity<>(categoryService.findCategoryByTitle(categoryTitle), HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<Void> updateCategoryTitle(@RequestBody UpdateCategoryRequestDto updateCategoryRequestDto) {
        categoryService.updateCategory(updateCategoryRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
