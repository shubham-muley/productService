package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductRequestDto;
import dev.shubham.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProductDetails(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(CreateProductRequestDto createProductRequestDto);
    public Product getProductByTitleAndCategory(String title, String categoryTitle);
    public Product getProductByTitle(String title);
    public void deleteProductById(Long id);
}
