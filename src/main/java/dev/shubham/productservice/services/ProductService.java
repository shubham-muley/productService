package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public Product getSingleProductDetails(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(CreateProductDto createProductDto);
}
