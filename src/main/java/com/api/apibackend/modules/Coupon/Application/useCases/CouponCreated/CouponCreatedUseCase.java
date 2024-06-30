/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Coupon.Application.useCases.CouponCreated;

import com.api.apibackend.modules.Coupon.Application.DTOs.CouponDTO;
import com.api.apibackend.modules.Coupon.Application.DTOs.response.CouponResponseDTO;
import com.api.apibackend.modules.Coupon.Domain.service.CouponCreatedService;
import com.api.apibackend.modules.Coupon.Infra.validation.CouponValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CouponCreatedUseCase {
    private CouponCreatedService couponCreatedService;

    @Autowired
    public CouponCreatedUseCase(CouponCreatedService couponCreatedService) {
        this.couponCreatedService = couponCreatedService;
    }

    public ResponseEntity<CouponResponseDTO> execute(CouponDTO couponDTO) {
        try {
            if (Optional.ofNullable(couponDTO).isPresent()) {
                log.error("O cupom não pode ser criado porque se encontra as informações estão vazias");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CouponResponseDTO("O cupom não pode ser criado porque se encontra as informações estão vazias",
                                this.getClass()
                                        .getName(),"Cupom não pode ser criado",null));
            }

            CouponValidation invalidCouponCreated = new CouponValidation();
            if (invalidCouponCreated.invalidCouponValues(couponDTO)) {
                log.error("O cupom não e válido para criação");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CouponResponseDTO("O cupom não pode ser criado porque contem erro de validação",
                        this.getClass()
                                .getName(), "Cupom não pode ser criado porque contem erros de validação", null));
            }

            return couponCreatedService.createCoupon(couponDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CouponResponseDTO(
                    "Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage(), null));
        }
    }
}
