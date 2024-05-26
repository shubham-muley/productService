package dev.shubham.productservice.services;

import dev.shubham.productservice.models.Product;

public interface ProductService {
    public Product getSingleProductDetails(Long id);
    public void getAllProducts();
}
