/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Supplier.Infra.validation;

import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import org.springframework.stereotype.Service;

@Service
public class SupplierValidation {
    public boolean isCnpjValid(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }

    public boolean isCompanyNameValid(String companyName) {
        return companyName != null && !companyName.isEmpty() && companyName.length() <= 100;
    }

    public boolean hasDataToUpdate(SupplierDTO supplierDTO) {
        return supplierDTO != null &&
                (supplierDTO.getCnpj() != null ||
                        supplierDTO.getContact() != null ||
                        supplierDTO.getNameCompany() != null ||
                        supplierDTO.getOfficeSupplier() != null ||
                        supplierDTO.getRegion() != null ||
                        supplierDTO.getSupplierAddress() != null);
    }
}