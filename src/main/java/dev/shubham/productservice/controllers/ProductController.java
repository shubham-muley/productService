package dev.shubham.productservice.controllers;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.dtos.ErrorDto;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.services.FakeStoreProductService;
import dev.shubham.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final FakeStoreProductService fakeStoreProductService;
    ProductService productService;

    public ProductController(ProductService productService, FakeStoreProductService fakeStoreProductService) {
        this.productService = productService;
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id){
        return productService.getSingleProductDetails(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProductDetails(@RequestBody CreateProductDto createProductDto){
        return productService.createProduct(createProductDto);
    }

    @GetMapping("/products")
    public ResponseEntity<Product[]> getAllProducts(){
        return productService.getAllProducts();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> nullPointerExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Some issue occured, Null Pointer Exception");
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }
}
