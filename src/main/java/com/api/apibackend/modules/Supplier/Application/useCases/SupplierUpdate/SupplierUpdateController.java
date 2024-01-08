package com.api.apibackend.modules.Supplier.Application.useCases.SupplierUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorEmptySupplier;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierUpdateController {
    private SupplierUpdateUseCase supplierUpdateUseCase;

    @Autowired
    public SupplierUpdateController(SupplierUpdateUseCase supplierUpdateUseCase) {
        this.supplierUpdateUseCase = supplierUpdateUseCase;
    }

    @PatchMapping(path = "/atualizar/{id}")
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
}
