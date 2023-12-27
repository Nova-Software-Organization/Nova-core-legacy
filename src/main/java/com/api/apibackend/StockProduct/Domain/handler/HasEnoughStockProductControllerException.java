package com.api.apibackend.StockProduct.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.api.apibackend.StockProduct.Domain.exception.HasEnoughStockProductException;

@ControllerAdvice
public class HasEnoughStockProductControllerException {
    @ExceptionHandler(HasEnoughStockProductException.class)
    public ResponseEntity<String> handleEnoughStockProduct(HasEnoughStockProductException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
