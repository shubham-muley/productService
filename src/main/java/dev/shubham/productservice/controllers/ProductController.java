package dev.shubham.productservice.controllers;

import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.services.FakeStoreProductService;
import dev.shubham.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Product getProductDetails(@PathVariable("id") Long id){
        return productService.getSingleProductDetails(id);
    }

}
