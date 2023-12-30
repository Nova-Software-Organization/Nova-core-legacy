package com.api.apibackend.MovementStock.Infra.persistence.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de movimentação do estoque dentro da empresa.
 */

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

/**
 * Representa uma entidade de movimentação de estoque no sistema.
 */
@Data
@Entity
@Table(name = "estoque_movimento")
@EqualsAndHashCode(of = "id")
public class StockMovementEntity {

    /**
     * Identificador único da movimentação de estoque.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Entidade de estoque associada à movimentação.
     */
    @ManyToOne
    @JoinColumn(name = "idestoque", nullable = false)
    private StockEntity stock;

    /**
     * Quantidade de saída na movimentação.
     */
    @Column(name = "quantidade_saida")
    private int outputQuantity;

    /**
     * Data da movimentação de estoque.
     */
    @Column(name = "data_movimentacao", nullable = false)
    private Date movementDate;

    /**
     * Construtor da entidade de movimentação de estoque.
     *
     * @param outputQuantity Quantidade de saída na movimentação.
     * @param movementDate    Data da movimentação de estoque.
     */
    public StockMovementEntity(int outputQuantity, Date movementDate) {
        this.outputQuantity = outputQuantity;
        this.movementDate = movementDate;
    }
}
