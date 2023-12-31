package com.api.apibackend.Coupon.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class CouponLimitException extends RuntimeException {
    public CouponLimitException(String message) {
        super(message);
    }
}
