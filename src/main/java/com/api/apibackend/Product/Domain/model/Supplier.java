package com.api.apibackend.Product.Domain.model;

import java.time.LocalDateTime;

import com.api.apibackend.SupplierAddress.infra.entity.SupplierAddressEntity;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class Supplier {
    private Long idSupplier;
    private String nameCompany;
    private String region;
    private String officeSupplier;
    private String contact;
    private String cnpj;
    private LocalDateTime date_created;

    @JsonAlias("supplierAddress")
    private SupplierAddressEntity supplierAddressEntity;

    public Supplier(Long idSupplier, String nameCompany, String region, String officeSupplier, LocalDateTime date_created, String contact, String cnpj, SupplierAddressEntity supplierAddressEntity) {
        this.idSupplier = idSupplier;
        this.nameCompany = nameCompany;
        this.region = region;
        this.officeSupplier = officeSupplier;
        this.date_created = date_created;
        this.contact = contact;
        this.cnpj = cnpj;
        this.supplierAddressEntity = supplierAddressEntity;
    }
}
