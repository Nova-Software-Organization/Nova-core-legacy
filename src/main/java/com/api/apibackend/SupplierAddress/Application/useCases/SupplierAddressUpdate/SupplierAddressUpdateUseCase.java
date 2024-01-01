package com.api.apibackend.SupplierAddress.Application.useCases.SupplierAddressUpdate;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.SupplierAddress.Domain.service.SupplierAddressCreatedService;
import com.api.apibackend.SupplierAddress.Domain.service.SupplierAddressUpdateService;

@Service
public class SupplierAddressUpdateUseCase {
    private SupplierAddressUpdateService supplierAddressUpdateService;
    private SupplierRepository supplierRepository;

    public ResponseEntity<ResponseMessageDTO> execute(Long id, SupplierAddressDTO supplierAddressDTO) {
        try {
            Optional<SupplierEntity> existingSupplier = supplierRepository
                    .findById(id);
            Boolean invalidSupplier = existingSupplier.isPresent()
                    ? existingSupplier.get().getSupplierAddressEntity().equals(supplierAddressDTO)
                    : null;

            if (invalidSupplier) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO(
                                "Fornecedor com o mesmo endereço informado. já existe por favor informe outro",
                                this.getClass().getName(), null));
            }

            return supplierAddressUpdateService.update(supplierAddressDTO.getIdSupplierAddress(), supplierAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
                            e.getMessage()));
        }
    }
}
