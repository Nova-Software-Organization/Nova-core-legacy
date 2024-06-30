/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.SupplierAddress.Application.useCases.SupplierAddressCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.modules.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;

@RestController
@RequestMapping("v1/fornecedor/endereco")
public class SupplierAddressCreatedController {
    private SupplierAddressCreatedUseCase supplierAddressCreatedUseCase;

    @Autowired
    public SupplierAddressCreatedController(SupplierAddressCreatedUseCase supplierAddressCreatedUseCase) {
        this.supplierAddressCreatedUseCase = supplierAddressCreatedUseCase;
    }

    @PostMapping("/criar")
    public ResponseEntity<ResponseMessageDTO> handle(SupplierAddressDTO supplierAddressDTO) {
        try {
            if (supplierAddressDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessageDTO("Contéudo do endereço do fornecedor indispónivel para criação!",
                                this.getClass().getName(), null));
            }

            return supplierAddressCreatedUseCase.execute(supplierAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO(
                    "Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}
