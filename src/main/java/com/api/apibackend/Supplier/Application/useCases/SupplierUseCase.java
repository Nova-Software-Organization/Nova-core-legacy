package com.api.apibackend.Supplier.Application.useCases;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Application.DTOs.SupplierRequest;
import com.api.apibackend.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.Supplier.Domain.service.SupplierService;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

@Service
public class SupplierUseCase {
    private SupplierService supplierService;

    public SupplierUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public List<SupplierEntity> executeList() {
        List<SupplierEntity> findAllSupplier = supplierService.listSupplier();
        return findAllSupplier;
    }

    public ResponseEntity<String> executeCreate(SupplierRequest supplierRequest) {
        try {            
            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }
            return supplierService.create(supplierRequest);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
