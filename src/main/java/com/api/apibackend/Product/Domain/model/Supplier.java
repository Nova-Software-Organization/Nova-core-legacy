package com.api.apibackend.Product.Domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Supplier {
    private Long idSupplier;
    private String nameCompany;
    private String region;
    private String officeSupplier;
    private String contact;
    private String cnpj;
    private LocalDateTime dateCreated;
    private SupplierAddress supplierAddress;

    public Supplier(
        Long idSupplier,
        String nameCompany,
        String region,
        String officeSupplier,
        LocalDateTime dateCreated,
        String contact,
        String cnpj,
        SupplierAddress supplierAddress
    ) {
        this.idSupplier = idSupplier;
        this.nameCompany = nameCompany;
        this.region = region;
        this.officeSupplier = officeSupplier;
        this.dateCreated = dateCreated;
        this.contact = contact;
        this.cnpj = cnpj;
        this.supplierAddress = supplierAddress;
    }

    public Supplier() {}
}
