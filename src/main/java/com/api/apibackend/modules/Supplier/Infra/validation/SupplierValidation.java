package com.api.apibackend.modules.Supplier.Infra.validation;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.stereotype.Service;

@Service
public class SupplierValidation {
    public boolean isCnpjValid(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }

    public boolean isCompanyNameValid(String companyName) {
        return companyName != null && !companyName.isEmpty() && companyName.length() <= 100;
    }
}