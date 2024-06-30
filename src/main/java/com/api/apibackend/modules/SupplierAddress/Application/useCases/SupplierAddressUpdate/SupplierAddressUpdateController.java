/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.SupplierAddress.Application.useCases.SupplierAddressUpdate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.modules.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;

@RestController
@RequestMapping("v1/fornecedor")
public class SupplierAddressUpdateController {
    private SupplierAddressUpdateUseCase supplierAddressUpdateUseCase;

    private ResponseEntity<ResponseMessageDTO> handle(@PathVariable Long id, SupplierAddressDTO supplierAddressDTO) {
        try {
            if (id == null || id <= 0 || id < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessageDTO("Contéudo do endereço do fornecedor indispónivel para criação!",
                                this.getClass().getName(), null));
            }

            return supplierAddressUpdateUseCase.execute(id, supplierAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO(
                    "Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}
