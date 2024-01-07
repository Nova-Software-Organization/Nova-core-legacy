package com.api.apibackend.modules.Order.infra.persistence.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Pedido no sistema.
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;
import com.api.apibackend.modules.OrderItem.infra.persistence.entity.OrderItemEntity;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.modules.Transaction.infra.entity.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa uma entidade de Pedido no sistema.
 */
@Lazy
@Data
@Entity
@Table(name = "pedido")
@EqualsAndHashCode(of = "numberOrder")
public class OrderEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * Número único do pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_numero_pedido")
    private Long numberOrder;
    
    /**
     * Cliente associado ao pedido.
     */
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private CustomerEntity client;
    
    /**
     * Lista de produtos associados ao pedido.
     */
    @ManyToMany
    @JoinTable(
    name = "pedido_produto",
    joinColumns = @JoinColumn(name = "idnumPed"),
    inverseJoinColumns = @JoinColumn(name = "idproduto")
    )
    @JsonBackReference
    private List<ProductEntity> products;

    /**
     * E-mail do cliente.
     */
    @Column(name = "email")
    private String customerEmail;

    /**
     * Status do pedido.
     */
    @Column(name = "status")
    private String status;

    /**
     * Data de pagamento do pedido.
     */
    @Column(name = "dtpagamento")
    private Date datePayment;

    /**
     * Nome do cliente.
     */
    @Column(name = "nmcliente")
    private String customerName;
    
    /**
     * Valor total do pedido.
     */
    @Column(name = "valorTotal")
    private Float totalValue;
    
    /**
     * Método de pagamento utilizado no pedido.
     */
    @Column(name = "metodo_pagamento")
    private String paymentMethod;

    /**
     * Entidade de transação associada ao pedido.
     */
    @ManyToOne
    @JoinColumn(name = "idtransacao", referencedColumnName = "idtransacao")
    private TransactionEntity transactionEntity;

    /**
     * Entidade de endereço de pedido associada ao pedido.
     */
    @OneToOne(mappedBy = "orderEntity")
    private OrderAddressEntity orderAddressEntity;

    /**
     * Calcula o valor total do pedido.
     * TODO - Refatorar e colocar regras de cálculo na DOMAIN.
     * Deixando a entidade OrderEntity sem nenhuma responsabilidade do banco de dados propriamente!
     */
    public void calculateTotal() {
        float total = 0.0f;
        for (ProductEntity product : products) {
            BigDecimal valuePrice = product.getPriceEntity().getPrice();
            if (product.getPriceEntity().getPrice() != null && valuePrice.floatValue() != 0.0f) {
                total += valuePrice.floatValue();
            }
        }
        setTotalValue(total);
    }
    
    /**
     * Adiciona um produto ao pedido.
     */
    public void addProduct(ProductEntity product) {
        if (product != null) {
            this.products.add(product);
        }
    }
    
    /**
     * Define a lista de produtos associados ao pedido.
     */
    public void setProducts(List<OrderItemEntity> orderItems) {
        this.products.clear();
        for (OrderItemEntity orderItem : orderItems) {
            ProductEntity product = orderItem.getProduct();
            this.products.add(product);
        }
    }

    /**
     * Limpa a lista de produtos do pedido.
     */
    public void clearProducts() {
        products.clear();
    }
}
