/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Produto dentro da empresa.
 */
package com.api.apibackend.modules.Product.Infra.persistence.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Price.infra.entity.PriceEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.Unity.infra.persistence.entity.UnityEntity;

import jakarta.persistence.CascadeType;
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

@Lazy
@Data
@Entity
@Table(name = "Produto")
@EqualsAndHashCode(of = "idProduct")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador único do produto.
     */
    @Id
    @Column(name = "idproduto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    /**
     * Entidade de mídia associada ao produto.
     */
    @OneToOne
    @JoinColumn(name = "idmid")
    private MidiaEntity midia;

    /**
     * Categoria à qual o produto pertence.
     */
    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", nullable = false)
    private ProductCategoryEntity category;

    /**
     * Nome do produto.
     */
    @Column(name = "nome")
    private String name;

    /**
     * Entidade do fornecedor associada ao produto.
     */
    @ManyToOne
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor")
    private SupplierEntity supplierEntity;

    /**
     * Entidade de preço associada ao produto.
     */
    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private PriceEntity priceEntity;

    /**
     * Entidade de estoque associada ao produto.
     */
    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private StockEntity stockEntity;

    /**
     * Unidade de medida associada ao produto.
     */
    @ManyToOne
    @JoinColumn(name = "idunidade", referencedColumnName = "idunidade")
    private UnityEntity unityEntity;

    /**
     * Descrição do produto.
     */
    @Column(name = "descricao")
    private String description;

    /**
     * Status de ativação do produto.
     */
    @Column(name = "ativo")
    private int status;

    /**
     * Código SKU (Stock Keeping Unit) do produto.
     */
    @Column(name = "sku")
    private String sku;
}
