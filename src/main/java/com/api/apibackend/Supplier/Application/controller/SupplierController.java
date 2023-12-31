package com.api.apibackend.Supplier.Application.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Supplier.Application.useCases.SupplierCreateUseCase;
import com.api.apibackend.Supplier.Application.useCases.SupplierDeactivateUseCase;
import com.api.apibackend.Supplier.Application.useCases.SupplierDeleteUseCase;
import com.api.apibackend.Supplier.Application.useCases.SupplierListUseCase;
import com.api.apibackend.Supplier.Application.useCases.SupplierUpdateUseCase;
import com.api.apibackend.Supplier.Domain.exception.ErrorEmptySupplier;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierController {
    private SupplierListUseCase supplierUseListCase;
    private SupplierCreateUseCase supplierCreateUseCase;
    private SupplierDeactivateUseCase supplierDeactivateUseCase;
    private SupplierDeleteUseCase supplierDeleteUseCase;
    private SupplierUpdateUseCase supplierUpdateUseCase;

    @Autowired
    public SupplierController(
        SupplierListUseCase supplierUseListCase,
        SupplierCreateUseCase supplierCreateUseCase,
        SupplierDeactivateUseCase supplierDeactivateUseCase,
        SupplierDeleteUseCase supplierDeleteUseCase,
        SupplierUpdateUseCase supplierUpdateUseCase
    ) {
        this.supplierUseListCase = supplierUseListCase;
        this.supplierCreateUseCase = supplierCreateUseCase;
        this.supplierDeactivateUseCase = supplierDeactivateUseCase;
        this.supplierDeleteUseCase = supplierDeleteUseCase;
        this.supplierUpdateUseCase = supplierUpdateUseCase;
    }

    @PostMapping(path = "/criar")
    @Tag(name = "Fornecedor", description = "Operações relacionadas a fornecedores")
    @Operation(summary = "Cria um novo fornecedor")
    public ResponseEntity<ResponseMessageDTO> create(
            @RequestBody SupplierDTO supplierRequest) {
        try {
            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }

            return supplierCreateUseCase.execute(supplierRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }

    @PostMapping(path = "/deletar/{id}")
    @Tag(name = "Fornecedor")
    @Operation(summary = "Deleta um fornecedor por ID")
    public ResponseEntity<ResponseMessageDTO> delete(
            @Parameter(description = "ID do fornecedor a ser deletado", required = true) @PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessageDTO("ID do fornecedor inválido", this.getClass().getName(), null));
            }

            return supplierDeleteUseCase.execute(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }

    @PostMapping(path = "/atualizar/{id}")
    @Tag(name = "Fornecedor")
    @Operation(summary = "Atualiza um fornecedor por ID")
    public ResponseEntity<ResponseMessageDTO> update(
            @Parameter(description = "ID do fornecedor a ser atualizado", required = true) @PathVariable Long id,
            @RequestBody SupplierDTO supplierRequest) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessageDTO("ID do cliente inválido", this.getClass().getName(), null));
            }

            if (supplierRequest == null) {
                throw new ErrorEmptySupplier("Erro: dados de fornecedor não fornecidos!");
            }

            return supplierUpdateUseCase.execute(id, supplierRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }

    @PostMapping(path = "/desativar/{id}")
    @Tag(name = "Fornecedor")
    @Operation(summary = "Desativa um fornecedor por ID")
    public ResponseEntity<ResponseMessageDTO> deactivate(
            @Parameter(description = "ID do fornecedor a ser desativado", required = true) @PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessageDTO("ID do cliente inválido", this.getClass().getName(), null));
            }

            return supplierDeactivateUseCase.execute(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }

    @PostMapping(path = "/listar")
    @Tag(name = "Fornecedor")
    @Operation(summary = "Lista todos os fornecedores")
    public ResponseEntity<List<SupplierEntity>> listSupplier() {
        try {
            List<SupplierEntity> suppliers = supplierUseListCase.execute();
            if (suppliers.isEmpty()) {
                throw new ErrorEmptySupplier("Lista de fornecedor está vazia");
            }

            return ResponseEntity.status(HttpStatus.OK).body(suppliers);
        } catch (ErrorEmptySupplier e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }
}
