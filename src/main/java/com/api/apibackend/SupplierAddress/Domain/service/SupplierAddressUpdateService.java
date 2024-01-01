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
import com.api.apibackend.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

@Service
public class SupplierAddressUpdateService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierAddressUpdateService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<ResponseMessageDTO> update(Long supplierId, SupplierAddressDTO updatedSupplierAddressDTO) {
        try {
            Optional<SupplierEntity> existingSupplier = supplierRepository.findById(supplierId);

            if (existingSupplier.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(
                        "Fornecedor não encontrado com o ID fornecido.", this.getClass().getName(), null));
            }

            SupplierEntity currentSupplier = existingSupplier.get();
            SupplierAddressEntity currentSupplierAddress = currentSupplier.getSupplierAddressEntity();

            currentSupplierAddress.setCep(updatedSupplierAddressDTO.getCep());
            currentSupplierAddress.setNeighborhood(updatedSupplierAddressDTO.getNeighborhood());
            currentSupplierAddress.setNumberHouseOrCompany(updatedSupplierAddressDTO.getNumberHouseOrCompany());
            currentSupplierAddress.setRoad(updatedSupplierAddressDTO.getRoad());

            currentSupplier.setCnpj(updatedSupplierAddressDTO.getSupplierDTO().getCnpj());
            currentSupplier.setContact(updatedSupplierAddressDTO.getSupplierDTO().getContact());
            currentSupplier.setRegion(updatedSupplierAddressDTO.getSupplierDTO().getRegion());
            currentSupplier.setNameCompany(updatedSupplierAddressDTO.getSupplierDTO().getNameCompany());
            currentSupplier.setOfficeSupplier(updatedSupplierAddressDTO.getSupplierDTO().getOfficeSupplier());
            currentSupplier.setStatus(updatedSupplierAddressDTO.getSupplierDTO().getStatus());

            supplierRepository.save(currentSupplier);

            return ResponseEntity.ok(new ResponseMessageDTO(
                    "Fornecedor e endereço atualizados com sucesso! ", this.getClass().getName(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
                            e.getMessage()));
        }
    }
}
