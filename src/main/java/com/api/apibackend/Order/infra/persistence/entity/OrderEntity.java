package com.api.apibackend.Order.infra.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.OrderItem.infra.entity.OrderItemEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Transaction.infra.entity.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Lazy
@Data
@Entity
@Table(name = "Pedido")
@EqualsAndHashCode(of = "id")
public class OrderEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_numero_pedido")
    private Long numberOrder;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private CustomerEntity client;
    
    @ManyToMany
    @JoinTable(
    name = "pedido_produto",
    joinColumns = @JoinColumn(name = "idnumPed"),
    inverseJoinColumns = @JoinColumn(name = "idproduto")
    )
    @JsonBackReference
    private List<ProductEntity> products;

    @Column(name = "email")
    private String customerEmail;

    @Column(name = "status")
    private String status;

    @Column(name = "dtpagamento")
    private Date datePayment;

    @Column(name = "nmcliente")
    private String customerName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "rua")
    private String road;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "bairro")
    private String neighborhood;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "nmcasa")
    private String housenumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "valorTotal")
    private Float totalValue;
    
    @Column(name = "metodo_pagamento")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "idtransacao", referencedColumnName = "idtransacao")
    private TransactionEntity transactionEntity;

    /**
     * TODO - refactor 
     * colocar regras de calculo na DOMAIN
     * Deixando a entidade orderEntity sem nenhuma resposibilidade do banco de dados propriamente!
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
    
    
    public void addProduct(ProductEntity product) {
        if (product != null) {
            this.products.add(product);
        }
    }
    
    public void setProducts(List<OrderItemEntity> orderItems) {
        this.products.clear();
        for (OrderItemEntity orderItem : orderItems) {
            ProductEntity product = orderItem.getProduct();
            this.products.add(product);
        }
    }

    public void clearProducts() {
        products.clear();
    }
}
