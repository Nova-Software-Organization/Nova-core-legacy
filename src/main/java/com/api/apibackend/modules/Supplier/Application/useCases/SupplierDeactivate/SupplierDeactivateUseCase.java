/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.useCases.SupplierDeactivate;

import com.api.apibackend.modules.Supplier.Domain.service.SupplierDeactivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Domain.service.SupplierCreatedService;

@Service
public class SupplierDeactivateUseCase {
    private SupplierDeactivateService supplierDeactivateService;

    @Autowired
    public SupplierDeactivateUseCase(SupplierDeactivateService supplierDeactivateService) {
        this.supplierDeactivateService = supplierDeactivateService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        return supplierDeactivateService.deactivate(id);
    }
}
