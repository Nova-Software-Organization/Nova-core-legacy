package com.api.apibackend.Supplier.Application.DTOs;

import lombok.Data;

@Data
public class SupplierAddressDTO {
    private Long idSupplierAddress;
    private String road;
    private String neighborhood;
    private String numberHouseOrCompany;
    private String cep;
}
