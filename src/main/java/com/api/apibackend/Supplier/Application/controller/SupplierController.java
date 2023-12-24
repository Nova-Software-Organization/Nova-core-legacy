package com.api.apibackend.Supplier.Application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Supplier.Application.DTOs.SupplierRequest;
import com.api.apibackend.Supplier.Domain.service.SupplierService;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierController {
    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public ResponseEntity<List<SupplierEntity>> listSupplier() {
        List<SupplierEntity> suppliers = supplierService.listSupplier();

        if (suppliers.isEmpty()) {
			return (ResponseEntity<List<SupplierEntity>>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}

        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

    public ResponseEntity<String> createSupplier(@RequestBody SupplierRequest supplierRequest) {
        
    }
}
