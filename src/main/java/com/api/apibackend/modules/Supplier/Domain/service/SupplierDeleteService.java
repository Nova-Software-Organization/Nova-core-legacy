package com.api.apibackend.modules.Supplier.Domain.service;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Infra.persistence.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplierDeleteService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierDeleteService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<ResponseMessageDTO> delete(Long supplierId) {
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
}
