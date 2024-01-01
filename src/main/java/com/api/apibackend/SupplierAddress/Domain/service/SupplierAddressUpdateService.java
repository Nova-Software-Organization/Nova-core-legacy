/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.SupplierAddress.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

import java.util.Optional;

@Service
public class SupplierAddressUpdateService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierAddressUpdateService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<ResponseMessageDTO> update(Long supplierId, SupplierAddressDTO updatedSupplierAddressDTO) {
        return supplierRepository.findById(supplierId)
                .map(existingSupplier -> {
                    SupplierAddressEntity currentSupplierAddress = existingSupplier.getSupplierAddressEntity();

                    currentSupplierAddress.setCep(updatedSupplierAddressDTO.getCep());
                    currentSupplierAddress.setNeighborhood(updatedSupplierAddressDTO.getNeighborhood());
                    currentSupplierAddress.setNumberHouseOrCompany(updatedSupplierAddressDTO.getNumberHouseOrCompany());
                    currentSupplierAddress.setRoad(updatedSupplierAddressDTO.getRoad());

                    existingSupplier.setCnpj(updatedSupplierAddressDTO.getSupplierDTO().getCnpj());
                    existingSupplier.setContact(updatedSupplierAddressDTO.getSupplierDTO().getContact());
                    existingSupplier.setRegion(updatedSupplierAddressDTO.getSupplierDTO().getRegion());
                    existingSupplier.setNameCompany(updatedSupplierAddressDTO.getSupplierDTO().getNameCompany());
                    existingSupplier.setOfficeSupplier(updatedSupplierAddressDTO.getSupplierDTO().getOfficeSupplier());
                    existingSupplier.setStatus(updatedSupplierAddressDTO.getSupplierDTO().getStatus());

                    supplierRepository.save(existingSupplier);

                    return ResponseEntity.ok(new ResponseMessageDTO(
                            "Fornecedor e endereço atualizados com sucesso! ", this.getClass().getName(), null));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(
                        "Fornecedor não encontrado com o ID fornecido.", this.getClass().getName(), null)));
    }
}
