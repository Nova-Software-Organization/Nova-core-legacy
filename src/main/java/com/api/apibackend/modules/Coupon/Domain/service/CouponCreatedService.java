/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Coupon.Domain.service;

import com.api.apibackend.modules.Coupon.Application.DTOs.CouponDTO;
import com.api.apibackend.modules.Coupon.Application.DTOs.response.CouponResponseDTO;
import com.api.apibackend.modules.Coupon.Infra.entity.CouponEntity;
import com.api.apibackend.modules.Coupon.Infra.repository.CouponRepository;
import com.api.apibackend.shared.helpers.ModelMappersConvertion;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class CouponCreatedService {

    private CouponRepository couponRepository;

    @Autowired
    public CouponCreatedService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public ResponseEntity<CouponResponseDTO> createCoupon(CouponDTO couponDTO) {
        List<String> errors = new ArrayList<>();
        try {
            if (Optional.ofNullable(couponDTO).isPresent()) {
                errors.add("O cupom não pode ser criado porque se encontra as informações estão vazias");
            }

            validateCouponValues(couponDTO, errors);
            if (!errors.isEmpty()) {
                logErrors(errors);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CouponResponseDTO(
                        "O cupom não pode ser criado devido a erros de validação", this.getClass().getName(), errors.toString(), null));
            }

            String couponCode = generateRandomCode();
            while (couponRepository.existsByCouponCode(couponCode)) {
                couponCode = generateRandomCode();
            }

            ModelMappersConvertion<CouponDTO, CouponEntity> toCouponDTOFromCouponEntity = new ModelMappersConvertion<>(new ModelMapper());
            CouponEntity newCouponGenerate = toCouponDTOFromCouponEntity.toDTOFromEntity(couponDTO, CouponEntity.class);
            newCouponGenerate.setCouponCode(couponCode);
            couponRepository.save(newCouponGenerate);

            log.info("Cupom criado com sucesso!", couponCode);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CouponResponseDTO("Cupom criado com sucesso", this.getClass().getName(), null, couponCode));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao processar a requisição", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CouponResponseDTO(
                    "Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage(), null));
        }
    }

    private String generateRandomCode() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }

    private void validateCouponValues(CouponDTO couponDTO, List<String> errors) {
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

    private void logErrors(List<String> errors) {
        for (String error : errors) {
            log.error(error);
        }
    }
}
