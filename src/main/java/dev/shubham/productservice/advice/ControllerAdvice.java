package dev.shubham.productservice.advice;

import dev.shubham.productservice.dtos.ErrorDto;
import dev.shubham.productservice.exceptions.NoProductsException;
import dev.shubham.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> nullPointerExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Some issue occured, Null Pointer Exception");
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> productNotFoundExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("No Product found");
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(NoProductsException.class)
    public ResponseEntity<ErrorDto> noProductsExceptionHandler(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("No Products found in the Database");
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }
}
