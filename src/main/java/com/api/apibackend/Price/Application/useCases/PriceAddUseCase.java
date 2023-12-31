package com.api.apibackend.Price.Application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.Price.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Price.Domain.service.PriceService;

@Service
public class PriceAddUseCase {
    private PriceService priceService;

    @Autowired
    public PriceAddUseCase(PriceService priceService) {
        this.priceService = priceService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id, PriceDTO priceDTO) {
        ResponseEntity<ResponseMessageDTO> validation = validationPrice(priceDTO);
        
        if (validation != null) {
            return validation;
        }

        return priceService.addPriceProduct(id, priceDTO);
    }

    public ResponseEntity<ResponseMessageDTO> validationPrice(PriceDTO priceDTO) {
        if (priceDTO.getDiscountPrice().intValue() < 0 && priceDTO.getPrice().intValue() < 0) {
            return ResponseEntity.badRequest().body(
                new ResponseMessageDTO("DTO de preço não fornecido!", this.getClass().getName(), null));
        }

        return null;
    }
}