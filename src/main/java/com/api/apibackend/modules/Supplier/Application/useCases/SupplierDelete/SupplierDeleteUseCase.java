/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.useCases.SupplierDelete;

import com.api.apibackend.modules.Supplier.Domain.service.SupplierDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Domain.service.SupplierCreatedService;

@Service
public class SupplierDeleteUseCase {
    private SupplierDeleteService supplierDeleteService;

    @Autowired
    public SupplierDeleteUseCase(SupplierDeleteService supplierDeleteService) {
        this.supplierDeleteService = supplierDeleteService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        return supplierDeleteService.delete(id);
    }
}
