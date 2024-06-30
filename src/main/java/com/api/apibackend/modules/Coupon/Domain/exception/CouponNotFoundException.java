package com.api.apibackend.modules.Coupon.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class CouponNotFoundException  extends Exception {
    public CouponNotFoundException(String message) {
        super(message);
    }
}
