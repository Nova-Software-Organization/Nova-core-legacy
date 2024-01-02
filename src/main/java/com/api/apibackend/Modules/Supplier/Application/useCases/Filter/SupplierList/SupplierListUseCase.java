/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.useCases.Filter.SupplierList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Domain.service.SupplierService;
import com.api.apibackend.modules.Supplier.Infra.entity.SupplierEntity;

@Service
public class SupplierListUseCase {
    private SupplierService supplierService;

    @Autowired
    public SupplierListUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public List<SupplierEntity> execute() {
        List<SupplierEntity> findAllSupplier = supplierService.listSupplier();
        return findAllSupplier;
    }
}
