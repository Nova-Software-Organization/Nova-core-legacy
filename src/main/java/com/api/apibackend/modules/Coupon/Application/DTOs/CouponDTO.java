/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Coupon.Application.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CouponDTO {
    private String couponCode;
    private String discountType;
    private double discountValue;
    private LocalDate expirationDate;
    private String conditions;
    private int usesRemaining;
    private String couponStatus;
    private int maxUsesPerCustomer;
    private String campaignCode;
    private LocalDate startDate;
    private String usageInformation;
    private String customerType;
    private double minimumOrderValue;
    private String notes;
    private String barcodeOrQRCode;
    private int status;
}
