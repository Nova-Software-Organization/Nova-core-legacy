package com.api.apibackend.modules.Midia.Application.useCases.CreatedMidia;

import com.api.apibackend.modules.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.modules.Midia.Domain.service.MidiaServiceCreated;
import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorEmptySupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MidiaUseCaseCreated {
    private MidiaServiceCreated midiaServiceCreated;

    @Autowired
    public MidiaUseCaseCreated(MidiaServiceCreated midiaServiceCreated) {
        this.midiaServiceCreated = midiaServiceCreated;
    }

    public ResponseEntity<ResponseMessageDTO> execute(MidiaDTO midiaDTO) {
        try {
            return midiaServiceCreated.create(midiaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}
