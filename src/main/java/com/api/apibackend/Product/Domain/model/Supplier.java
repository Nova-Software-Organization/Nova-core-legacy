package com.api.apibackend.Product.Domain.model;

import java.time.LocalDateTime;

import com.api.apibackend.SupplierAddress.infra.entity.SupplierAddressEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supplier {
    private Long idSupplier;
    private String nameCompany;
    private String region;
    private String officeSupplier;
    private LocalDateTime date_creaated;
    private String contact;
    private String cnpj;
    private SupplierAddressEntity supplierAddressEntity;
}
