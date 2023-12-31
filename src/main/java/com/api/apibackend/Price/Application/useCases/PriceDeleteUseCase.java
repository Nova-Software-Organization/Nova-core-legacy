package com.api.apibackend.Price.Application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.api.apibackend.Price.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Price.Domain.service.PriceService;

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
