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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public class CouponCreatedController {
    private CouponCreatedUseCase couponCreatedUseCase;

    @Autowired
    public CouponCreatedController(CouponCreatedUseCase couponCreatedUseCase) {
        this.couponCreatedUseCase = couponCreatedUseCase;
    }

    @PostMapping(path = "/criar")
    @Tag(name = "Cupom", description = "Operações relacionadas ao cupom de desconto")
    @Operation(summary = "Cria um novo cupom de desconto para um produto ou N produtos em questão")
    public ResponseEntity<CouponResponseDTO> handle(@RequestBody CouponDTO couponDTO) {
        try {
            Optional.ofNullable(couponDTO).orElseThrow();
            return couponCreatedUseCase.execute(couponDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CouponResponseDTO(
                    "Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage(), null));
        }
    }
}