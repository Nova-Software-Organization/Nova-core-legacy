/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.useCases.SupplierCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.modules.Supplier.Domain.service.SupplierCreatedService;

@Service
public class SupplierCreateUseCase {
    private SupplierCreatedService supplierService;

    @Autowired
    public SupplierCreateUseCase(SupplierCreatedService supplierService) {
        this.supplierService = supplierService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(SupplierDTO supplierRequest) {
        try {
            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }

            return supplierService.create(supplierRequest);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}