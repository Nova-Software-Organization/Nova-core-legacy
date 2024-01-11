package com.api.apibackend.modules.Coupon.Application.useCases.CouponDeactivate;

import com.api.apibackend.modules.Coupon.Application.DTOs.CouponDTO;
import com.api.apibackend.modules.Coupon.Application.DTOs.response.CouponResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/cupom")
public class CouponDeactivateController {
    private CouponDeactivateUseCase couponDeactivateUseCase;

    @Autowired
    public CouponDeactivateController(CouponDeactivateUseCase couponDeactivateUseCase) {
        this.couponDeactivateUseCase = couponDeactivateUseCase;
    }

    @PostMapping("/desativar")
    public ResponseEntity<CouponResponseDTO> handle(@RequestBody CouponDTO couponDTO) {
        return Optional.ofNullable(couponDTO)
                .filter(coupon -> coupon.getCouponCode() != null && !coupon.getCouponCode().isEmpty() && coupon.getStatus() == 0)
                .map(coupon -> {
                    couponDeactivateUseCase.execute(coupon.getCouponCode());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new CouponResponseDTO("Cupom atualizado com sucesso!", this.getClass().getName(), null, null));
                })
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CouponResponseDTO("Cupom não pode ser atualizado!", this.getClass().getName(),
                                "Erro de validação encontrado. Código do cupom não fornecido ou cupom não está inativo.", null)));
    }
}
