/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Coupon.Application.DTOs.response;

public class CouponResponseDTO {
    private String message;
    private String className;
    private String errorMessage;

    private String codeCoupon;

    public CouponResponseDTO(String message, String className, String errorMessage, String codeCoupon) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
        this.codeCoupon = codeCoupon;
    }
}
