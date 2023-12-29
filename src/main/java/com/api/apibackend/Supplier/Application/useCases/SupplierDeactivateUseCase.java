package com.api.apibackend.Supplier.Application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Domain.service.SupplierService;

@Service
public class SupplierDeactivateUseCase {
    private SupplierService supplierService;

    @Autowired
    public SupplierDeactivateUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public ResponseEntity<String> execute(Long id) {
        return supplierService.deactivateSupplier(id);
    }
}
