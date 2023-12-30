package com.api.apibackend.CustomerAddress.infra.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de endereço do cliente dentro da empresa.
 */

import java.io.Serializable;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa uma entidade de endereço no sistema.
 */
@Data
@Entity
@Table(name = "Endereco")
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity implements Serializable {

    /**
     * Identificador único do endereço.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Long id;

    /**
     * Cliente associado ao endereço.
     */
    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private CustomerEntity customerEntity;

    /**
     * Rua do endereço.
     */
    @NotBlank(message = "A rua não pode estar em branco")
    @Column(name = "rua")
    private String road;

    /**
     * Bairro do endereço.
     */
    @NotBlank(message = "O bairro não pode estar em branco")
    @Column(name = "bairro")
    private String neighborhood;

    /**
     * Número da casa do endereço.
     */
    @NotBlank(message = "O número da casa não pode estar em branco")
    @Column(name = "numCasa")
    private String housenumber;

    /**
     * Estado do endereço.
     */
    @Column(name = "estado")
    private String state;

    /**
     * CEP do endereço.
     */
    @NotBlank(message = "O cep não pode estar em branco")
    @Column(name = "cep")
    private String cep;

    /**
     * Construtor padrão.
     */
    public AddressEntity() { }

    /**
     * Obtém o cliente associado ao endereço.
     *
     * @return Cliente associado ao endereço.
     */
    @JsonProperty("client")
    public CustomerEntity getcustomerEntity() {
        return this.customerEntity;
    }

    /**
     * Verifica se dois endereços são iguais.
     *
     * @param other Outro endereço a ser comparado.
     * @return True se os endereços são iguais, False caso contrário.
     */
    public boolean isSameAddress(AddressEntity other) {
        return this.getRoad().equals(other.getRoad()) &&
               this.getNeighborhood().equals(other.getNeighborhood()) &&
               this.getHousenumber().equals(other.getHousenumber()) &&
               this.getState().equals(other.getState()) &&
               this.getCep().equals(other.getCep());
    }
}
