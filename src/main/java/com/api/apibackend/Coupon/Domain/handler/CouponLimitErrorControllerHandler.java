package com.api.apibackend.Coupon.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.Coupon.Domain.exception.CouponLimitException;

@ControllerAdvice
public class CouponLimitErrorControllerHandler {
    @ExceptionHandler(CouponLimitException.class)
    public ResponseEntity<String> handleCouponLimitFailedException(CouponLimitException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
