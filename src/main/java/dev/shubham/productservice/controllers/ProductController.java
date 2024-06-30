package dev.shubham.productservice.controllers;

import dev.shubham.productservice.dtos.CreateProductRequestDto;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("DbProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getSingleProductDetails(id), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProductDetails(@RequestBody CreateProductRequestDto createProductRequestDto){
        return new ResponseEntity<>(productService.createProduct(createProductRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{categoryTitle}/{productTitle}")
    public ResponseEntity<Product> getProductByTitleAndCategory(@PathVariable String categoryTitle, @PathVariable String productTitle){
        return new ResponseEntity<>(productService.getProductByTitleAndCategory(productTitle, categoryTitle), HttpStatus.OK);
    }

    @GetMapping("/products/title/{productTitle}")
    public ResponseEntity<Product> getProductByTitle(@PathVariable String productTitle){
        return new ResponseEntity<>(productService.getProductByTitle(productTitle), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
