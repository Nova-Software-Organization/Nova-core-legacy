package com.api.apibackend.SupplierAddress.infra.persistence.entity;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "endereco_fornecedor")
public class SupplierAddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_ed_fornecedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplierAddress;

    @Column(name = "rua")
    private String road;

    @Column(name = "bairro")
    private String neighborhood;

    @Column(name = "numero")
    private String numberHouseOrCompany;

    @Column(name = "cep")
    private String cep;
}
