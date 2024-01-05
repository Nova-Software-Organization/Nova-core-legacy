/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Price.Application.useCases.PriceDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Price.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Modules.Price.Domain.service.PriceService;

@Service
public class PriceDeleteUseCase {
    private PriceService priceService;

    @Autowired
    public PriceDeleteUseCase(PriceService priceService) {
        this.priceService = priceService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        return priceService.deletePrice(id);
    }
}
