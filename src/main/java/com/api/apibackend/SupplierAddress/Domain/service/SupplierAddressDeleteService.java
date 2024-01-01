/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.SupplierAddress.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.SupplierAddress.infra.repository.SupplierAddressRepository;

@Service
public class SupplierAddressDeleteService {
    private SupplierRepository supplierRepository;
    private SupplierAddressRepository supplierAddressRepository;

    @Autowired
    public SupplierAddressDeleteService(SupplierRepository supplierRepository,
            SupplierAddressRepository supplierAddressRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierAddressRepository = supplierAddressRepository;
    }

    public ResponseEntity<ResponseMessageDTO> delete(Long supplierId) {
        try {
            Optional<SupplierEntity> existingSupplier = supplierRepository.findById(supplierId);

            if (existingSupplier.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(
                        "Fornecedor não encontrado com o ID fornecido.", this.getClass().getName(), null));
            }

            SupplierEntity currentSupplier = existingSupplier.get();

            // Excluir o fornecedor e seu endereço associado
            supplierRepository.delete(currentSupplier);

            return ResponseEntity.ok(new ResponseMessageDTO(
                    "Fornecedor e endereço excluídos com sucesso! ", this.getClass().getName(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
                            e.getMessage()));
        }
    }
}
