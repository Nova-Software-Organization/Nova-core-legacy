package com.api.apibackend.OrderItem.infra.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;

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

@Lazy
@Data
@Entity
@Table(name = "PedidoItem")
@EqualsAndHashCode(of = "id")
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedItem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idnumPed")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private ProductEntity product;

    @Column(name = "quantidade")
    private int quantity;

    @Column(name = "precoUni")
    private BigDecimal unitPrice;
}
