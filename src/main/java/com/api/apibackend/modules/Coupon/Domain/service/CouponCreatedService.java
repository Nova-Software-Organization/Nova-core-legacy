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
import com.api.apibackend.modules.Coupon.Domain.provider.GenerateCouponProvider;
import com.api.apibackend.modules.Coupon.Infra.entity.CouponEntity;
import com.api.apibackend.modules.Coupon.Infra.repository.CouponRepository;
import com.api.apibackend.modules.Coupon.Infra.validation.CouponValidation;
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
    private GenerateCouponProvider generateCouponProvider;

    @Autowired
    public CouponCreatedService(CouponRepository couponRepository, GenerateCouponProvider generateCouponProvider) {
        this.couponRepository = couponRepository;
        this.generateCouponProvider = generateCouponProvider;
    }

    public ResponseEntity<CouponResponseDTO> createCoupon(CouponDTO couponDTO) {
        List<String> errors = new ArrayList<>();
        try {
            if (Optional.ofNullable(couponDTO).isPresent()) {
                errors.add("O cupom não pode ser criado porque se encontra as informações estão vazias");
            }

            CouponValidation couponValidation = new CouponValidation();
            couponValidation.validateCouponValues(couponDTO, errors);
            if (!errors.isEmpty()) {
                logErrors(errors);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CouponResponseDTO(
                        "O cupom não pode ser criado devido a erros de validação", this.getClass().getName(), errors.toString(), null));
            }

            String couponCode = generateCouponProvider.generateRandomCode();
            while (couponRepository.existsByCouponCode(couponCode)) {
                couponCode = generateCouponProvider.generateRandomCode();
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

    private void logErrors(List<String> errors) {
        for (String error : errors) {
            log.error(error);
        }
    }
}
