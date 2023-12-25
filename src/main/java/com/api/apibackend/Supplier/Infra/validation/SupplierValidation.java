package com.api.apibackend.Supplier.Infra.validation;

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