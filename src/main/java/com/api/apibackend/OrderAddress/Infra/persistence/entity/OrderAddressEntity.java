package com.api.apibackend.OrderAddress.Infra.persistence.entity;

import java.io.Serializable;

import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Long id;

    @NotBlank(message = "A rua não pode estar em branco")
    @Column(name = "rua")
    private String road;

    @NotBlank(message = "O bairro não pode estar em branco")
    @Column(name = "bairro")
    private String neighborhood;

    @NotBlank(message = "O número da casa não pode estar em branco")
    @Column(name = "numCasa")
    private String housenumber;

    @Column(name = "estado")
    private String state;

    @NotBlank(message = "O cep não pode estar em branco")
    @Column(name = "cep")
    private String cep;

    @OneToOne
    @JoinColumn(name = "numberOrder")
    private OrderEntity orderEntity;
}
