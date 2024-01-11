package com.api.apibackend.modules.Supplier.Domain.service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.Supplier.Infra.persistence.repository.SupplierRepository;
import com.api.apibackend.modules.Supplier.Infra.validation.SupplierValidation;
import com.api.apibackend.modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class SupplierUpdateService {
    private SupplierRepository supplierRepository;
    private SupplierValidation supplierValidation;

    @Autowired
    public SupplierUpdateService(SupplierRepository supplierRepository, SupplierValidation supplierValidation) {
        this.supplierRepository = supplierRepository;
        this.supplierValidation = supplierValidation;
    }

    public ResponseEntity<ResponseMessageDTO> update(Long supplierId, @RequestBody SupplierDTO updatedSupplier) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }

            SupplierValidation validationSupplier = new SupplierValidation();
            SupplierEntity existingSupplier = supplierRepository.findById(supplierId).orElse(null);
            if (existingSupplier == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("fornecedor não encontrado", this.getClass().getName(), null));
            }

            if (validationSupplier.hasDataToUpdate(updatedSupplier)) {
                SupplierAddressEntity supplierAddressEntity = new SupplierAddressEntity();
                supplierAddressEntity.setCep(updatedSupplier.getSupplierAddress().getCep());
                supplierAddressEntity.setNeighborhood(updatedSupplier.getSupplierAddress().getNeighborhood());
                supplierAddressEntity.setNumberCompany(updatedSupplier.getSupplierAddress().getNumberHouseOrCompany());
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
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Fornecedor atualizado com sucesso", this.getClass().getName(), null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Nenhum dado do fornecedor atualizado", this.getClass().getName(), null));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Erro ao atualizar o fornecedor", this.getClass().getName(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}
