package com.api.apibackend.StockProduct.Domain.exception;

public class HasEnoughStockProductException extends Exception {
    public HasEnoughStockProductException(String message) {
        super(message);
    }
}
