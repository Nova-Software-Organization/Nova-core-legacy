package com.api.apibackend.Supplier.Application.useCases.SupplierDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Supplier.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/fornecedor")
public class SupplierDeleteController {
    private SupplierDeleteUseCase supplierDeleteUseCase;

    @Autowired
    public SupplierDeleteController(SupplierDeleteUseCase supplierDeleteUseCase) {
        this.supplierDeleteUseCase = supplierDeleteUseCase;
    }

    @DeleteMapping(path = "/deletar/{id}")
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
}
