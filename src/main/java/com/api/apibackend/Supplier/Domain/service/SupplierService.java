package com.api.apibackend.Supplier.Domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Supplier.Application.DTOs.SupplierRequest;
import com.api.apibackend.Supplier.Domain.exception.ErrorValidationSupplier;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.Supplier.Infra.validation.SupplierValidation;
import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierValidation supplierValidation;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, SupplierValidation supplierValidation) {
        this.supplierRepository = supplierRepository;
        this.supplierValidation = supplierValidation;
    }

    public List<SupplierEntity> listSupplier() {
        return supplierRepository.findAll();
    }

    public ResponseEntity<String> create(@RequestBody SupplierRequest supplierRequest) {
        SupplierEntity supplierEntity = null;

        try {
            if (!supplierValidation.isCnpjValid(supplierRequest.getCnpj())) {
                throw new ErrorValidationSupplier("CNPJ inválido");
            }

            if (!supplierValidation.isCompanyNameValid(supplierRequest.getNameCompany())) {
                throw new ErrorValidationSupplier("Nome da empresa inválido");
            }

            SupplierAddressEntity supplierAddressEntity = new SupplierAddressEntity();
            supplierAddressEntity.setCep(supplierRequest.getSupplierAddress().getCep());
            supplierAddressEntity.setNeighborhood(supplierRequest.getSupplierAddress().getNeighborhood());
            supplierAddressEntity
                    .setNumberHouseOrCompany(supplierRequest.getSupplierAddress().getNumberHouseOrCompany());
            supplierAddressEntity.setRoad(supplierRequest.getSupplierAddress().getRoad());

            supplierEntity = new SupplierEntity();
            supplierEntity.setCnpj(supplierRequest.getCnpj());
            supplierEntity.setContact(supplierRequest.getContact());
            supplierEntity.setDateCreated(new Date());
            supplierEntity.setNameCompany(supplierRequest.getNameCompany());
            supplierEntity.setOfficeSupplier(supplierRequest.getOfficeSupplier());
            supplierEntity.setRegion(supplierRequest.getRegion());
            supplierEntity.setSupplierAddressEntity(supplierAddressEntity);
            supplierEntity.setStatus(1);

            supplierRepository.save(supplierEntity);
        } catch (ErrorValidationSupplier e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Erro de validações no fornecedor: " + e.getMessage());
        }

        if (supplierEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Fornecedor adicionado com sucesso: " + supplierEntity);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar fornecedor");
    }

    public ResponseEntity<String> deleteSupplier(Long supplierId) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }

            if (!supplierRepository.existsById(supplierId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
            }

            supplierRepository.deleteById(supplierId);
            return ResponseEntity.status(HttpStatus.OK).body("Fornecedor deletado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar fornecedor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar fornecedor");
        }
    }

    public ResponseEntity<String> updateSupplier(Long supplierId, @RequestBody SupplierRequest updatedSupplier) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }

            SupplierEntity existingSupplier = supplierRepository.findById(supplierId)
                    .orElse(null);

            if (existingSupplier == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
            }

            SupplierAddressEntity supplierAddressEntity = new SupplierAddressEntity();
            supplierAddressEntity.setCep(updatedSupplier.getSupplierAddress().getCep());
            supplierAddressEntity.setNeighborhood(updatedSupplier.getSupplierAddress().getNeighborhood());
            supplierAddressEntity
                    .setNumberHouseOrCompany(updatedSupplier.getSupplierAddress().getNumberHouseOrCompany());
            supplierAddressEntity.setRoad(updatedSupplier.getSupplierAddress().getRoad());

            existingSupplier.setCnpj(updatedSupplier.getCnpj());
            existingSupplier.setContact(updatedSupplier.getContact());
            existingSupplier.setDateCreated(new Date());
            existingSupplier.setNameCompany(updatedSupplier.getNameCompany());
            existingSupplier.setOfficeSupplier(updatedSupplier.getOfficeSupplier());
            existingSupplier.setRegion(updatedSupplier.getRegion());
            existingSupplier.setStatus(1);
            existingSupplier.setSupplierAddressEntity(supplierAddressEntity);

            supplierRepository.save(existingSupplier);
            return ResponseEntity.status(HttpStatus.OK).body("Fornecedor atualizado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao atualizar fornecedor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar fornecedor");
        }
    }

    public ResponseEntity<String> deactivateSupplier(Long supplierId) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }

            SupplierEntity existingSupplier = supplierRepository.findById(supplierId)
                    .orElse(null);

            if (existingSupplier == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
            }

            existingSupplier.setStatus(0);

            supplierRepository.save(existingSupplier);
            return ResponseEntity.status(HttpStatus.OK).body("Fornecedor desativado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao desativar fornecedor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar fornecedor");
        }
    }
}
