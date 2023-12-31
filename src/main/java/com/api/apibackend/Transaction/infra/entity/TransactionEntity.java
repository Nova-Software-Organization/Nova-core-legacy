package com.api.apibackend.Transaction.infra.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * Entidade responsavel por gerenciar toda a parte de transações da aplicação confirmação de pagamento de um pedido
 * ----------------------------------------------------------------------------
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Lazy
@Data
@Entity
@Table(name = "transacao")
public class TransactionEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

      /**
     * Identificador único da transação.
     */
    @Id
    @Column(name = "idtransacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Número do cartão associado à transação.
     */
    @Column(name = "cardNum")
    private String cardNumber;

    /**
     * CPF associado à transação.
     */
    @CPF
    @Column(name = "cpf")
    private String cpf;

    /**
     * Data da compra realizada na transação.
     */
    @Column(name = "dtCompra")
    private Date purchaseDate;

    /**
     * Status da transação.
     */
    @Column(name = "status")
    private String status;

    /**
     * Valor total da transação.
     */
    @Column(name = "valorTotal")
    private BigDecimal value;

    /**
     * Número de parcelas da transação.
     */
    @Column(name = "parcela")
    private String installments;
}
