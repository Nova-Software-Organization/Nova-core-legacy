/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.useCases.Filter.SupplierList;

import java.util.List;

import com.api.apibackend.modules.Supplier.Domain.service.filter.SupplierFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Domain.service.SupplierCreatedService;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;

@Service
public class SupplierListUseCase {
    private SupplierFilterList supplierFilterList;

    @Autowired
    public SupplierListUseCase(SupplierFilterList supplierFilterList) {
        this.supplierFilterList = supplierFilterList;
    }

    public List<SupplierEntity> execute() {
        List<SupplierEntity> findAllSupplier = supplierFilterList.list();
        return findAllSupplier;
    }
}
