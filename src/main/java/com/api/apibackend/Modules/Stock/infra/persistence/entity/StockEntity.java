package com.api.apibackend.Modules.Stock.infra.persistence.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Estoque no sistema.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.apibackend.Modules.MovementStock.Infra.persistence.entity.StockMovementEntity;
import com.api.apibackend.Modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Modules.Unity.infra.persistence.entity.UnityEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "estoque")
@EqualsAndHashCode(of = "idestoque")
public class StockEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
      /**
     * Identificador único do estoque.
     */
    @Id
    @Column(name = "idestoque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    /**
     * Entidade do produto associado ao estoque.
     */
    @OneToOne
    @JoinColumn(name = "idproduto")
    private ProductEntity productEntity;

    /**
     * Entidade da unidade associada ao estoque.
     */
    @ManyToOne
    @JoinColumn(name = "idunidade", nullable = false)
    private UnityEntity unityEntity;

    /**
     * Quantidade de itens adicionados ao estoque.
     */
    @Column(name = "quantidade_entrada")
    private int input_quantity;

    /**
     * Quantidade de itens retirados do estoque.
     */
    @Column(name = "quantidade_saida")
    private int output_quantity;

    /**
     * Tipo de movimentação no estoque (entrada/saída).
     */
    @Column(name = "tipo_movimentacao", nullable = false)
    private String typeMoviment;

    /**
     * Data da movimentação no estoque.
     */
    @Column(name = "data_movimentacao", nullable = false)
    private Date dateMoviment;

    /**
     * Lista de movimentações associadas a este estoque.
     */
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockMovementEntity> movements = new ArrayList<>();
}
