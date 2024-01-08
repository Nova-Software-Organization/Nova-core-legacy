/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Price.Application.useCases.PriceUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.modules.Price.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Price.Domain.service.PriceService;

@Service
public class PriceUpdateUseCase {
    private PriceService priceService;

    @Autowired
    public PriceUpdateUseCase(PriceService priceService) {
        this.priceService = priceService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id, PriceDTO priceDTO) {
        ResponseEntity<ResponseMessageDTO> validation = validationPrice(priceDTO);
        if (validation != null) {
            return validation;
        }

        return priceService.updatePriceProduct(id, priceDTO);
    }

    public ResponseEntity<ResponseMessageDTO> validationPrice(PriceDTO priceDTO) {
        if (priceDTO.getDiscountPrice().intValue() < 0 && priceDTO.getPrice().intValue() < 0) {
            return ResponseEntity.badRequest().body(
                    new ResponseMessageDTO("DTO de preço não fornecido!", this.getClass().getName(), null));
        }

        return null;
    }
}
