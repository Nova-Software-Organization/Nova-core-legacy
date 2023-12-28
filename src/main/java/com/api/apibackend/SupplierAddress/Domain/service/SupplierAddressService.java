package com.api.apibackend.SupplierAddress.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;
import com.api.apibackend.SupplierAddress.infra.repository.SupplierAddressRepository;


@Service
public class SupplierAddressService {
    private SupplierAddressRepository supplierAddressRepository;

    @Autowired
    public SupplierAddressService(SupplierAddressRepository supplierAddressRepository) {
        this.supplierAddressRepository = supplierAddressRepository;
    }

    public ResponseEntity<String> updateSupplierAddress(Long supplierAddressId, SupplierAddressDTO updatedSupplierAddress) {
        try {
            if (supplierAddressId == null || supplierAddressId <= 0) {
                throw new IllegalArgumentException("ID do endereço de fornecedor inválido");
            }

            SupplierAddressEntity existingSupplierAddress = supplierAddressRepository.findById(supplierAddressId)
                    .orElse(null);

            if (existingSupplierAddress == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço de fornecedor não encontrado");
            }

            existingSupplierAddress.setCep(updatedSupplierAddress.getCep());
            existingSupplierAddress.setNeighborhood(updatedSupplierAddress.getNeighborhood());
            existingSupplierAddress.setNumberHouseOrCompany(updatedSupplierAddress.getNumberHouseOrCompany());
            existingSupplierAddress.setRoad(updatedSupplierAddress.getRoad());

            supplierAddressRepository.save(existingSupplierAddress);
            return ResponseEntity.status(HttpStatus.OK).body("Endereço de fornecedor atualizado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar endereço de fornecedor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar endereço de fornecedor");
        }
    }
}
