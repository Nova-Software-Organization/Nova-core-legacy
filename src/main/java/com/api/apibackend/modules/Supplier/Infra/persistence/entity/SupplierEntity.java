/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de fornecedor dentro da empresa.
 */
 package com.api.apibackend.modules.Supplier.Infra.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.modules.Brand.infra.persistence.entity.BrandEntity;
import com.api.apibackend.modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa uma entidade de fornecedor no sistema.
 */
@Lazy
@Data
@Entity
@Table(name = "fornecedor")
@EqualsAndHashCode(of = "id")
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do fornecedor.
     */
    @Id
    @NotBlank
    @Column(name = "idfornecedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;

    /**
     * Nome da empresa do fornecedor.
     */
    @Column(name = "nm_empresa")
    private String nameCompany;

    /**
     * Região do fornecedor.
     */
    @Column(name = "regiao")
    private String region;

    /**
     * Cargo do fornecedor.
     */
    @Column(name = "cargo_fornecedor")
    private String officeSupplier;

    /**
     * Data de criação do fornecedor.
     */
    @Column(name = "dt_criacao")
    private Date dateCreated;

    /**
     * Contato do fornecedor.
     */
    @Column(name = "contato")
    private String contact;

    /**
     * CNPJ do fornecedor.
     */
    @Column(name = "cnpj")
    private String cnpj;

    /**
     * Status do fornecedor.
     */
    @Column(name = "status")
    private int status;

    /**
     * Entidade de endereço do fornecedor.
     */
    @ManyToOne
    @JoinColumn(name = "id_ed_fornecedor", referencedColumnName = "id_ed_fornecedor")
    private SupplierAddressEntity supplierAddressEntity;

    /**
     * Lista de marcas fornecidas pelo fornecedor.
     */
    @ManyToMany
    @JoinTable(name = "fornecedor_marca",
            joinColumns = @JoinColumn(name = "idmarca"),
            inverseJoinColumns = @JoinColumn(name = "idfornecedor"))
    private List<BrandEntity> brands;
}
