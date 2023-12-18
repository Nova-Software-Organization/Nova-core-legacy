package com.api.apibackend.Product.Infra.entity;

import java.io.Serializable;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

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
@Table(name = "Produto")
@EqualsAndHashCode(of = "idProduct")
public class ProductEntity implements Serializable {

    @Id
    @Column(name = "idproduto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @OneToOne
    @JoinColumn(name = "idmid")
    private MidiaEntity midia;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", nullable = false)
    private ProductCategoryEntity category;

    @Column(name = "nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor")
    private SupplierEntity supplierEntity;

    @OneToMany
    @JoinColumn(name = "idpreco", referencedColumnName = "idpreco")
    private PriceEntity priceEntity;

    @Column(name = "estoque")
    private int quantityInStock;

    @Column(name = "descricao")
    private String description;

    @Column(name = "ativo")
    private int status;
}
