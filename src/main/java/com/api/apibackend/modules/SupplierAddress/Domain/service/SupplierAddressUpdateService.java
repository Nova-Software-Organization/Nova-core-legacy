/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.SupplierAddress.Domain.service;

import com.api.apibackend.shared.util.ValidationAreEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Infra.persistence.repository.SupplierRepository;
import com.api.apibackend.modules.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.modules.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

import java.util.Objects;
import java.util.function.Consumer;

@Service
public class SupplierAddressUpdateService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierAddressUpdateService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<ResponseMessageDTO> update(Long supplierId, SupplierAddressDTO updatedSupplierAddressDTO) {
        return supplierRepository.findById(supplierId)
                .map(existingSupplier -> {
                    SupplierAddressEntity currentSupplierAddress = existingSupplier.getSupplierAddressEntity();
                    ValidationAreEqual validationAreEqual = new ValidationAreEqual();
                    boolean isDataUpdated = !validationAreEqual.areEqual(currentSupplierAddress.getCep(), updatedSupplierAddressDTO.getCep(), currentSupplierAddress::setCep) ||
                            !validationAreEqual.areEqual(currentSupplierAddress.getNeighborhood(), updatedSupplierAddressDTO.getNeighborhood(), currentSupplierAddress::setNeighborhood) ||
                            !validationAreEqual.areEqual(currentSupplierAddress.getNumberCompany(), updatedSupplierAddressDTO.getNumberHouseOrCompany(), currentSupplierAddress::setNumberCompany) ||
                            !validationAreEqual.areEqual(currentSupplierAddress.getRoad(), updatedSupplierAddressDTO.getRoad(), currentSupplierAddress::setRoad) ||
                            !validationAreEqual.areEqual(existingSupplier.getCnpj(), updatedSupplierAddressDTO.getSupplierDTO().getCnpj(), existingSupplier::setCnpj) ||
                            !validationAreEqual.areEqual(existingSupplier.getContact(), updatedSupplierAddressDTO.getSupplierDTO().getContact(), existingSupplier::setContact) ||
                            !validationAreEqual.areEqual(existingSupplier.getRegion(), updatedSupplierAddressDTO.getSupplierDTO().getRegion(), existingSupplier::setRegion) ||
                            !validationAreEqual.areEqual(existingSupplier.getNameCompany(), updatedSupplierAddressDTO.getSupplierDTO().getNameCompany(), existingSupplier::setNameCompany) ||
                            !validationAreEqual.areEqual(existingSupplier.getOfficeSupplier(), updatedSupplierAddressDTO.getSupplierDTO().getOfficeSupplier(), existingSupplier::setOfficeSupplier) ||
                            !validationAreEqual.areEqual(existingSupplier.getStatus(), updatedSupplierAddressDTO.getSupplierDTO().getStatus(), existingSupplier::setStatus);

                    if (isDataUpdated) {
                        supplierRepository.save(existingSupplier);
                        return ResponseEntity.ok(new ResponseMessageDTO("Fornecedor e endereço atualizados com sucesso! ", this.getClass().getName(), null));
                    } else {
                        return ResponseEntity.ok(new ResponseMessageDTO("Nenhum dado foi alterado. Nenhuma atualização necessária.", this.getClass().getName(), null));
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("Fornecedor não encontrado com o ID fornecido.", this.getClass().getName(), null)));
    }
}
