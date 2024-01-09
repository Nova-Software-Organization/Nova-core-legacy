/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;

public interface ISupplierService {
    public List<SupplierEntity> listSupplier();
    public ResponseEntity<ResponseMessageDTO> create(@RequestBody SupplierDTO supplierRequest);
    public ResponseEntity<ResponseMessageDTO> deleteSupplier(Long supplierId);
    public ResponseEntity<ResponseMessageDTO> updateSupplier(Long supplierId, @RequestBody SupplierDTO updatedSupplier);
    public ResponseEntity<ResponseMessageDTO> deactivateSupplier(Long supplierId);
}
