package com.api.apibackend.Supplier.Application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.Supplier.Domain.service.SupplierService;

public class SupplierUpdateUseCase {
    private SupplierService supplierService;

    @Autowired
    public SupplierUpdateUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public ResponseEntity<String> execute(Long id, SupplierDTO supplierRequest) {
        try {
            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }
            
            return supplierService.updateSupplier(id, supplierRequest);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
