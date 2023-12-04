package com.api.apibackend.Product.infra.entity;

import java.io.Serializable;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Produto")
@EqualsAndHashCode(of = "id")
public class ProductEntity implements Serializable {

    @Id
    @Column(name = "idproduto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idmid")
    private MidiaEntity midia;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", nullable = false)
    private ProductCategoryEntity category;

    @Column(name = "nome")
    private String name;

    @Column(name = "preco")
    private Double price;

    @Column(name = "preco_de")
    private Double dePrice;

    @Column(name = "estoque")
    private int quantityInStock;

    @Column(name = "descricao")
    private String description;
}


