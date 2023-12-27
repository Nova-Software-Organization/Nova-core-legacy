package com.api.apibackend.Coupon.Domain.exception;

public class CouponLimitException extends RuntimeException {
    public CouponLimitException(String message) {
        super(message);
    }
}
