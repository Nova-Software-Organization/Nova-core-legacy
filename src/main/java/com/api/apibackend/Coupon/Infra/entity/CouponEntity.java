package com.api.apibackend.Coupon.Infra.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "cupom")
@EqualsAndHashCode(of = "id")
public class CouponEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idcoupon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cupom", unique = true, length = 50)
    private String couponCode;

    @Column(name = "tipo_desconto", length = 20)
    private String discountType;

    @Column(name = "valor_desconto")
    private double discountValue;

    @Column(name = "data_expiracao")
    private LocalDate expirationDate;

    @Column(name = "condicoes", length = 255)
    private String conditions;

    @Column(name = "usos_restantes")
    private int usesRemaining;

    @Column(name = "status_cupom", length = 20)
    private String couponStatus;

    @Column(name = "usos_maximos_cliente")
    private int maxUsesPerCustomer;

    @Column(name = "codigo_campanha", length = 50)
    private String campaignCode;

    @Column(name = "data_inicio")
    private LocalDate startDate;

    @Column(name = "informacoes_uso", length = 255)
    private String usageInformation;

    @Column(name = "tipo_cliente", length = 50)
    private String customerType;

    @Column(name = "valor_minimo_pedido")
    private double minimumOrderValue;

    @Column(name = "notas", length = 255)
    private String notes;

    @Column(name = "data_criacao")
    private LocalDate creationDate;

    @Column(name = "codigo_barras_qr", length = 100)
    private String barcodeOrQRCode;

    @Column(name = "ativo")
    private int status;
}
