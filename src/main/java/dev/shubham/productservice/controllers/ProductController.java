package dev.shubham.productservice.controllers;

import dev.shubham.productservice.dtos.CreateProductRequestDto;
import dev.shubham.productservice.dtos.CreateProductResponseDto;
import dev.shubham.productservice.exceptions.NoProductsException;
import dev.shubham.productservice.exceptions.ProductNotFoundException;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("DbProductService") ProductService productService) {
        this.productService = productService;
    }

    // TODO: Add update Product using PutMapping and PatchMapping
    // TODO: Add relevant Request DTOs
    // TODO: Add relevant Response DTOs

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getSingleProductDetails(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        return new ResponseEntity<>(productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage()), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws NoProductsException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{categoryTitle}/{productTitle}")
    public ResponseEntity<Product> getProductByTitleAndCategory(@PathVariable String categoryTitle, @PathVariable String productTitle) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProductByTitleAndCategory(productTitle, categoryTitle), HttpStatus.OK);
    }

    @GetMapping("/title/{productTitle}")
    public ResponseEntity<Product> getProductByTitle(@PathVariable String productTitle) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProductByTitle(productTitle), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
