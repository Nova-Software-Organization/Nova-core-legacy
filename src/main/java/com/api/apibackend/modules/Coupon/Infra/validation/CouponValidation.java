package com.api.apibackend.modules.Coupon.Infra.validation;

import com.api.apibackend.modules.Coupon.Application.DTOs.CouponDTO;

import java.time.LocalDate;
import java.util.List;

public class CouponValidation {
    public void validateCouponValues(CouponDTO couponDTO, List<String> errors) {
        if (couponDTO == null) {
            errors.add("O cupom não pode ser criado porque as informações estão vazias");
            return;
        }

        if (couponDTO.getDiscountValue() < 0) {
            errors.add("Valor de desconto inválido");
        }

        if (couponDTO.getUsesRemaining() < 0) {
            errors.add("Número de usos restantes inválido");
        }

        if (couponDTO.getExpirationDate() != null && couponDTO.getExpirationDate().isBefore(LocalDate.now())) {
            errors.add("Data de expiração inválida");
        }

        if (couponDTO.getStartDate() != null && couponDTO.getStartDate().isAfter(LocalDate.now())) {
            errors.add("Data de início inválida");
        }
    }

    public boolean invalidCouponValues(CouponDTO couponDTO) {
        return couponDTO.getDiscountValue() < 0 ||
                couponDTO.getUsesRemaining() < 0 ||
                couponDTO.getMaxUsesPerCustomer() < 0 ||
                couponDTO.getMinimumOrderValue() < 0 ||
                couponDTO.getStatus() < 0;
    }
}
