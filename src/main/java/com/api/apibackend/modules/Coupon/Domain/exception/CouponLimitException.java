/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Coupon.Domain.exception;

public class CouponLimitException extends RuntimeException {
    public CouponLimitException(String message) {
        super(message);
    }
}
