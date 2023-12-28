package com.api.apibackend.MovementStock.Infra.persistence.entity;

import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Unity.infra.entity.UnityEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movimentacao_estoque")
public class MovementStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idunidade", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "idunidade", nullable = false)
    private UnityEntity unityEntity;

    @Column(name = "quantidade")
    private int quantity;
}
