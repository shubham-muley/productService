package dev.shubham.productservice.controllers;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.dtos.ErrorDto;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<Product> createProductDetails(@RequestBody CreateProductDto createProductDto){
        return new ResponseEntity<>(productService.createProduct(createProductDto), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> nullPointerExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Some issue occured, Null Pointer Exception");
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }
}
