package com.api.apibackend.modules.Supplier.Application.useCases.SupplierCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorEmptySupplier;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierCreatedController {
    private SupplierCreateUseCase supplierCreateUseCase;

    @Autowired
    public SupplierCreatedController(SupplierCreateUseCase supplierCreateUseCase) {
        this.supplierCreateUseCase = supplierCreateUseCase;
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
}
