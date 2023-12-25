package com.api.apibackend.Supplier.Application.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Supplier.Application.DTOs.SupplierRequest;
import com.api.apibackend.Supplier.Application.useCases.SupplierUseCase;
import com.api.apibackend.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierController {
    private SupplierUseCase supplierUseCase;

    @Autowired
    public SupplierController(SupplierUseCase supplierUseCase) {
        this.supplierUseCase = supplierUseCase;
    }

    public ResponseEntity<List<SupplierEntity>> listSupplier() {
        try {
            List<SupplierEntity> suppliers = supplierUseCase.executeList();
            if (suppliers.isEmpty()) {
                throw new ErrorEmptySupplier("Lista de fornecedor está vazia");
            }

            return ResponseEntity.status(HttpStatus.OK).body(suppliers);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @PostMapping(path = "/criar")
    public ResponseEntity<String> createSupplier(@RequestBody SupplierRequest supplierRequest) {
        try {
            return supplierUseCase.executeCreate(supplierRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
