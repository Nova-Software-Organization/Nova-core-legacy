/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Supplier.Application.useCases.Filter.SupplierList;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierListController {
    private SupplierListUseCase supplierListUseCase;

    @Autowired
    public SupplierListController(SupplierListUseCase supplierListUseCase) {
        this.supplierListUseCase = supplierListUseCase;
    }

    @GetMapping(path = "/listar")
    @Tag(name = "Fornecedor")
    @Operation(summary = "Lista todos os fornecedores")
    public ResponseEntity<List<SupplierEntity>> listSupplier() {
        try {
            List<SupplierEntity> suppliers = supplierListUseCase.execute();
            if (suppliers.isEmpty()) {
                throw new ErrorEmptySupplier("Lista de fornecedor está vazia");
            }

            return ResponseEntity.status(HttpStatus.OK).body(suppliers);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }
}
