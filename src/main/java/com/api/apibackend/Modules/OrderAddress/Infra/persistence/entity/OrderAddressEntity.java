/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Entidade responsavel por armazenar os endereços dos pedidos
 */

package com.api.apibackend.Modules.OrderAddress.Infra.persistence.entity;

import java.io.Serializable;

import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido_endereco")
public class OrderAddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do endereço do pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Long id;

    /**
     * Rua do endereço do pedido.
     */
    @NotBlank(message = "A rua não pode estar em branco")
    @Column(name = "rua")
    private String road;

    /**
     * Bairro do endereço do pedido.
     */
    @NotBlank(message = "O bairro não pode estar em branco")
    @Column(name = "bairro")
    private String neighborhood;

    /**
     * Número da casa do endereço do pedido.
     */
    @NotBlank(message = "O número da casa não pode estar em branco")
    @Column(name = "numCasa")
    private String housenumber;

    /**
     * Estado do endereço do pedido.
     */
    @Column(name = "estado")
    private String state;

    /**
     * CEP do endereço do pedido.
     */
    @NotBlank(message = "O CEP não pode estar em branco")
    @Column(name = "cep")
    private String cep;

    /**
     * Entidade de pedido associada a este endereço.
     */
    @OneToOne
    @JoinColumn(name = "numberOrder")
    private OrderEntity orderEntity;
}
