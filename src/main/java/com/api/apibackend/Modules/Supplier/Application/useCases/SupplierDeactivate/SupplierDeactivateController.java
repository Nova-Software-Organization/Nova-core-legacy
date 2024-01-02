package com.api.apibackend.Modules.Supplier.Application.useCases.SupplierDeactivate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Modules.Supplier.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierDeactivateController {
    private SupplierDeactivateUseCase supplierDeactivateUseCase;

    @Autowired
    public SupplierDeactivateController(SupplierDeactivateUseCase supplierDeactivateUseCase) {
        this.supplierDeactivateUseCase = supplierDeactivateUseCase;
    }

    @PatchMapping(path = "/desativar/{id}")
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
}
