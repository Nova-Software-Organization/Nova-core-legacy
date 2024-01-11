package com.api.apibackend.modules.Coupon.Domain.service;

import com.api.apibackend.modules.Coupon.Application.DTOs.response.CouponResponseDTO;
import com.api.apibackend.modules.Coupon.Infra.entity.CouponEntity;
import com.api.apibackend.modules.Coupon.Infra.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CouponDeactivateService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponDeactivateService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public ResponseEntity<CouponResponseDTO> deactivateCoupon(String codeCoupon) {
        try {
            CouponEntity existingCoupon = couponRepository.findByCouponCode(codeCoupon)
                    .orElse(null);

            if (existingCoupon == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CouponResponseDTO("Cupom não encontrado", this.getClass().getName(), null, codeCoupon));
            }
            existingCoupon.setStatus(0);
            couponRepository.save(existingCoupon);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CouponResponseDTO("Cupom desativado com sucesso!", this.getClass().getName(), null, codeCoupon));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CouponResponseDTO("Ocorreu um erro ao desativar o cupom!", this.getClass().getName(), e.getMessage(), codeCoupon));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CouponResponseDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage(), null));
        }
    }
}
