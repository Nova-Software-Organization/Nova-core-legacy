/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.SupplierAddress.Application.useCases.SupplierAddressDelete;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.modules.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.modules.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.SupplierAddress.Domain.service.SupplierAddressDeleteService;

@Service
public class SupplierAddressDeleteUseCase {
    private SupplierRepository supplierRepository;
    private SupplierAddressDeleteService supplierAddressDeleteService;

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        try {
            Optional<SupplierEntity> existingSupplier = supplierRepository
                    .findById(id);

            if (!existingSupplier.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO(
                                "Fornecedor não existe, por favor informe outro",
                                this.getClass().getName(), null));
            }

            return supplierAddressDeleteService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
                            e.getMessage()));
        }
    }
}
