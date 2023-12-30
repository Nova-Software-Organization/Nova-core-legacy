package com.api.apibackend.Supplier.Application.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

public interface ISupplierService {
    public List<SupplierEntity> listSupplier();
    public ResponseEntity<ResponseMessageDTO> create(@RequestBody SupplierDTO supplierRequest);
    public ResponseEntity<ResponseMessageDTO> deleteSupplier(Long supplierId);
    public ResponseEntity<ResponseMessageDTO> updateSupplier(Long supplierId, @RequestBody SupplierDTO updatedSupplier);
    public ResponseEntity<ResponseMessageDTO> deactivateSupplier(Long supplierId);
}
