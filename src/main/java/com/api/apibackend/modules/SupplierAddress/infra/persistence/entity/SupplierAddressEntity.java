/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Endereço do Fornecedor.
 */

package com.api.apibackend.modules.SupplierAddress.infra.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa uma entidade de Endereço do Fornecedor.
 */
@Data
@Entity
@EqualsAndHashCode(of = "idSupplierAddress")
@Table(name = "endereco_fornecedor")
public class SupplierAddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do endereço do fornecedor.
     */
    @Id
    @Column(name = "id_ed_fornecedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplierAddress;

    /**
     * Rua do endereço do fornecedor.
     */
    @Column(name = "rua")
    private String road;

    /**
     * Bairro do endereço do fornecedor.
     */
    @Column(name = "bairro")
    private String neighborhood;

    /**
     * Número da casa ou empresa no endereço do fornecedor.
     */
    @Column(name = "numero")
    private String numberCompany;

    /**
     * CEP (Código de Endereçamento Postal) do endereço do fornecedor.
     */
    @Column(name = "cep")
    private String cep;
}
