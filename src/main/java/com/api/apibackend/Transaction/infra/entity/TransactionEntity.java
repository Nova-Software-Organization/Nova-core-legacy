package com.api.apibackend.Transaction.infra.entity;

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

    @Id
    @Column(name = "idtransacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cardNum")
    private String cardNumber;

    @CPF
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "dtCompra")
    private Date purchase_date;

    @Column(name = "status")
    private String status;

    @Column(name = "valorTotal")
    private BigDecimal value;

    @Column(name = "parcela")
    private String installments;
}
