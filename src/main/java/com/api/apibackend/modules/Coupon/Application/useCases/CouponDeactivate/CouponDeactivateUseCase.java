package com.api.apibackend.modules.Coupon.Application.useCases.CouponDeactivate;

import com.api.apibackend.modules.Coupon.Application.DTOs.response.CouponResponseDTO;
import com.api.apibackend.modules.Coupon.Domain.service.CouponDeactivateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CouponDeactivateUseCase {
    private CouponDeactivateService couponDeactivateService;

    @Autowired
    public CouponDeactivateUseCase(CouponDeactivateService couponDeactivateService) {
        this.couponDeactivateService = couponDeactivateService;
    }

    public ResponseEntity<CouponResponseDTO> execute(String codeCoupon) {

        return couponDeactivateService.deactivateCoupon(codeCoupon);
    }
}
