/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de items dentro carrinho associados a um cliente.
 */

package com.api.apibackend.modules.CartItem.Infra.persistence.entity;

import java.io.Serializable;

import com.api.apibackend.modules.Cart.Infra.persistence.entity.CartEntity;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;

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
@Table(name = "item_carrinho")
public class CartItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarrinhoItem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idcarrinho")
    private CartEntity cartEntity;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private ProductEntity product;

    @Column(name = "quantidade")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariantEntity productVariant;

    @Column(name = "amount")
    private Integer amount;
}
