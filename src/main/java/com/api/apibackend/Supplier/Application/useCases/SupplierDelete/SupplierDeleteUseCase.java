/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Supplier.Application.useCases.SupplierDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Supplier.Domain.service.SupplierService;

@Service
public class SupplierDeleteUseCase {
    private SupplierService supplierService;

    @Autowired
    public SupplierDeleteUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        return supplierService.deleteSupplier(id);
    }
}
