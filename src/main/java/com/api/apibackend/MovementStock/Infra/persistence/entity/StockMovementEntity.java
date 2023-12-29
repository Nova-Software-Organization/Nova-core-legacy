package com.api.apibackend.MovementStock.Infra.persistence.entity;

import java.util.Date;

import com.api.apibackend.Stock.infra.persistence.entity.StockEntity;

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
@Table(name = "estoque_movimento")
@EqualsAndHashCode(of = "id")
public class StockMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idestoque", nullable = false)
    private StockEntity stock;

    @Column(name = "quantidade_saida")
    private int outputQuantity;

    @Column(name = "data_movimentacao", nullable = false)
    private Date movementDate;

    public StockMovementEntity(int outputQuantity, Date movementDate) {
        this.outputQuantity = outputQuantity;
        this.movementDate = movementDate;
    }
}
