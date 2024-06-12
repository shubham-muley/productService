package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<Product> getSingleProductDetails(Long id);
    public ResponseEntity<Product[]> getAllProducts();
    public ResponseEntity<Product> createProduct(CreateProductDto createProductDto);
}
