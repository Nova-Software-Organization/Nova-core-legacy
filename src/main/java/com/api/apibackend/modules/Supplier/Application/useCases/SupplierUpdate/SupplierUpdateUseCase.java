/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.useCases.SupplierUpdate;

import com.api.apibackend.modules.Supplier.Domain.service.SupplierDeleteService;
import com.api.apibackend.modules.Supplier.Domain.service.SupplierUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.modules.Supplier.Domain.service.SupplierCreatedService;

@Service
public class SupplierUpdateUseCase {
    private SupplierUpdateService supplierUpdateService;

    @Autowired
    public SupplierUpdateUseCase(SupplierUpdateService supplierUpdateService) {
        this.supplierUpdateService = supplierUpdateService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id, SupplierDTO supplierRequest) {
        try {
            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }
            return supplierUpdateService.update(id, supplierRequest);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessageDTO("dados do fornecedor vázios", this.getClass().getName(), e.getMessage()));
        }
    }
}
