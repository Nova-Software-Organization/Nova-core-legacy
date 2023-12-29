package com.api.apibackend.Supplier.Application.DTOs;

import java.util.Date;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long idSupplier;
    private String nameCompany;
    private String region;
    private String officeSupplier;
    private Date dateCreated;
    private String contact;
    private String cnpj;
    private SupplierAddressDTO supplierAddress;
}
