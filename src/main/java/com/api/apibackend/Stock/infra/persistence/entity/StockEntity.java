package com.api.apibackend.Stock.infra.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.apibackend.MovementStock.Infra.persistence.entity.StockMovementEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Unity.infra.persistence.entity.UnityEntity;

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
    
    @Id
    @Column(name = "idestoque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @OneToOne
    @JoinColumn(name = "idproduto")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "idunidade", nullable = false)
    private UnityEntity unityEntity;

    @Column(name = "quantidade_entrada")
    private int input_quantity;

    @Column(name = "quantidade_saida")
    private int output_quantity;

    @Column(name = "tipo_movimentacao", nullable = false)
    private String typeMoviment;

    @Column(name = "data_movimentacao", nullable = false)
    private Date dateMoviment;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockMovementEntity> movements = new ArrayList<>();
}
