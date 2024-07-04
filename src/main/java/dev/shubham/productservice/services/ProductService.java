package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductRequestDto;
import dev.shubham.productservice.exceptions.NoProductsException;
import dev.shubham.productservice.exceptions.ProductNotFoundException;
import dev.shubham.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProductDetails(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts() throws NoProductsException;
    public Product createProduct(CreateProductRequestDto createProductRequestDto);
    public Product getProductByTitleAndCategory(String title, String categoryTitle) throws ProductNotFoundException;
    public Product getProductByTitle(String title) throws ProductNotFoundException;
    public void deleteProductById(Long id) throws ProductNotFoundException;
}
