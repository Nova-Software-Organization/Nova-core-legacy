/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.SupplierAddress.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Infra.persistence.repository.SupplierRepository;
import com.api.apibackend.modules.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.SupplierAddress.infra.repository.SupplierAddressRepository;

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
        return supplierRepository.findById(supplierId)
                .map(existingSupplier -> {
                    supplierRepository.delete(existingSupplier);
                    return ResponseEntity.ok(new ResponseMessageDTO(
                            "Fornecedor e endereço excluídos com sucesso! ", this.getClass().getName(), null));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(
                        "Fornecedor não encontrado com o ID fornecido.", this.getClass().getName(), null)));
    }
}
