package com.api.apibackend.Supplier.Domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierEntity> listSupplier() {
        return supplierRepository.findAll();
    }
}
