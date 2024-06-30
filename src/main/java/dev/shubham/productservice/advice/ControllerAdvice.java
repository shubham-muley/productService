package dev.shubham.productservice.advice;

import dev.shubham.productservice.dtos.ErrorDto;
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
}
