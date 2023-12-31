package com.api.apibackend.Supplier.Domain.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Supplier.Application.repository.ISupplierService;
import com.api.apibackend.Supplier.Domain.exception.ErrorValidationSupplier;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.Supplier.Infra.validation.SupplierValidation;
import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

@Service
public class SupplierService implements ISupplierService {
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

    public ResponseEntity<ResponseMessageDTO> create(@RequestBody SupplierDTO supplierRequest) {
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
                    .body(new ResponseMessageDTO("Erro de validações no fornecedor", this.getClass().getName(), e.getMessage()));
        }

        if (supplierEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMessageDTO("Fornecedor adicionado com sucesso", this.getClass().getName(), null));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), null));
    }

    public ResponseEntity<ResponseMessageDTO> deleteSupplier(Long supplierId) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }

            if (!supplierRepository.existsById(supplierId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("fornecedor não encontrado", this.getClass().getName(), null));
            }

            supplierRepository.deleteById(supplierId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("fornecedor deletado com sucesso", this.getClass().getName(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("erro ao deletar o fornecedor", this.getClass().getName(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }

    public ResponseEntity<ResponseMessageDTO> updateSupplier(Long supplierId, @RequestBody SupplierDTO updatedSupplier) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }
    
            SupplierEntity existingSupplier = supplierRepository.findById(supplierId).orElse(null);
    
            if (existingSupplier == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("fornecedor não encontrado", this.getClass().getName(), null));
            }
    
            if (hasDataToUpdate(updatedSupplier)) {
                SupplierAddressEntity supplierAddressEntity = new SupplierAddressEntity();
                supplierAddressEntity.setCep(updatedSupplier.getSupplierAddress().getCep());
                supplierAddressEntity.setNeighborhood(updatedSupplier.getSupplierAddress().getNeighborhood());
                supplierAddressEntity.setNumberHouseOrCompany(updatedSupplier.getSupplierAddress().getNumberHouseOrCompany());
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
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("fornecedor deletado com sucesso", this.getClass().getName(), null));
            } else {
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("nenhum dado do fornecedor atualizado", this.getClass().getName(), null));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("erro ao deletar o fornecedor", this.getClass().getName(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
    
    private boolean hasDataToUpdate(SupplierDTO supplierDTO) {
        return supplierDTO != null &&
               (supplierDTO.getCnpj() != null ||
                supplierDTO.getContact() != null ||
                supplierDTO.getNameCompany() != null ||
                supplierDTO.getOfficeSupplier() != null ||
                supplierDTO.getRegion() != null ||
                supplierDTO.getSupplierAddress() != null);
    }

    public ResponseEntity<ResponseMessageDTO> deactivateSupplier(Long supplierId) {
        try {
            if (supplierId == null || supplierId <= 0) {
                throw new IllegalArgumentException("ID de fornecedor inválido");
            }

            SupplierEntity existingSupplier = supplierRepository.findById(supplierId)
                    .orElse(null);

            if (existingSupplier == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("fornecedor não encontrado", this.getClass().getName(), null));
            }

            existingSupplier.setStatus(0);

            supplierRepository.save(existingSupplier);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("fornecedor desativado com sucesso!", this.getClass().getName(), null));
        } catch (IllegalArgumentException e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ocorreu um erro ao desativar o fornecedor!", this.getClass().getName(), e.getMessage()));
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}
