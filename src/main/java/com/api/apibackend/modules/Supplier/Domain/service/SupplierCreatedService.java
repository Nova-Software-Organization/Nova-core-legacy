/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Application.repository.ISupplierService;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorValidationSupplier;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.Supplier.Infra.persistence.repository.SupplierRepository;
import com.api.apibackend.modules.Supplier.Infra.validation.SupplierValidation;
import com.api.apibackend.modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

@Service
public class SupplierCreatedService implements ISupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierValidation supplierValidation;

    @Autowired
    public SupplierCreatedService(SupplierRepository supplierRepository, SupplierValidation supplierValidation) {
        this.supplierRepository = supplierRepository;
        this.supplierValidation = supplierValidation;
    }

    public ResponseEntity<ResponseMessageDTO> create(SupplierDTO supplierRequest) {
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
                    .setNumberCompany(supplierRequest.getSupplierAddress().getNumberHouseOrCompany());
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
                    .body(new ResponseMessageDTO("Erro de validações no fornecedor", this.getClass().getName(), e.getMessage()));
        }

        if (supplierEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMessageDTO("Fornecedor adicionado com sucesso", this.getClass().getName(), null));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), null));
    }
}
