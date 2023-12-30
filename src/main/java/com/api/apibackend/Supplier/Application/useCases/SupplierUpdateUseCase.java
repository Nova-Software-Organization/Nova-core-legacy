package com.api.apibackend.Supplier.Application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.Supplier.Domain.service.SupplierService;

@Service
public class SupplierUpdateUseCase {
    private SupplierService supplierService;

    @Autowired
    public SupplierUpdateUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id, SupplierDTO supplierRequest) {
        try {
            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }
            
            return supplierService.updateSupplier(id, supplierRequest);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("dados do fornecedor vázios", this.getClass().getName(), e.getMessage()));
        }
    }
}
