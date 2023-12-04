package com.api.apibackend.Supplier.Infra.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import com.api.apibackend.SupplierAddress.infra.entity.SupplierAddressEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "fornecedor")
@EqualsAndHashCode(of = "id")
public class SupplierEntity implements Serializable {
    
    @Id
    @NotBlank
    @Column(name = "idfornecedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsupplier;

    @Column(name = "nm_empresa")
    private String nameCompany;

    @Column(name = "regiao")
    private String region;

    @Column(name = "cargo_fornecedor")
    private String officeSupplier;
    
    @CurrentTimestamp
    @Column(name = "dt_criacao")
    private LocalDateTime date_creaated;

    @Column(name = "contato")
    private String contact;

    @CNPJ
    @Column(name = "cnpj")
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "id_ed_fornecedor", referencedColumnName = "id_ed_fornecedor")
    private SupplierAddressEntity supplierAddressEntity;
}
