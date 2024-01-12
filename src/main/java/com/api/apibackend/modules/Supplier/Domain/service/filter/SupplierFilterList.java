package com.api.apibackend.modules.Supplier.Domain.service.filter;

import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.Supplier.Infra.persistence.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierFilterList {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierFilterList(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierEntity> list() {
        return supplierRepository.findAll();
    }
}
