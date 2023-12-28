package com.api.apibackend.Stock.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "estoque")
@EqualsAndHashCode(of = "id")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @Column(name = "idproduto", nullable = false)
    private Long productId;

    @Column(name = "idunidade", nullable = false)
    private Long unitId;

    @Column(name = "quantity")
    private Integer quantity;
}
