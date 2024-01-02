/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Coupon.Infra.entity;

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

    /**
     * Identificador único do cupom.
     */
    @Id
    @Column(name = "idcoupon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código único do cupom.
     */
    @Column(name = "codigo_cupom", unique = true, length = 50)
    private String couponCode;

    /**
     * Tipo de desconto do cupom (ex: "Porcentagem", "Valor Fixo").
     */
    @Column(name = "tipo_desconto", length = 20)
    private String discountType;

    /**
     * Valor do desconto aplicado pelo cupom.
     */
    @Column(name = "valor_desconto")
    private double discountValue;

    /**
     * Data de expiração do cupom.
     */
    @Column(name = "data_expiracao")
    private LocalDate expirationDate;

    /**
     * Condições ou termos associados ao cupom.
     */
    @Column(name = "condicoes", length = 255)
    private String conditions;

    /**
     * Usos restantes disponíveis para o cupom.
     */
    @Column(name = "usos_restantes")
    private int usesRemaining;

    /**
     * Status atual do cupom.
     */
    @Column(name = "status_cupom", length = 20)
    private String couponStatus;

    /**
     * Número máximo de vezes que um cliente pode usar o cupom.
     */
    @Column(name = "usos_maximos_cliente")
    private int maxUsesPerCustomer;

    /**
     * Código da campanha associada ao cupom.
     */
    @Column(name = "codigo_campanha", length = 50)
    private String campaignCode;

    /**
     * Data de início de validade do cupom.
     */
    @Column(name = "data_inicio")
    private LocalDate startDate;

    /**
     * Informações sobre o uso do cupom.
     */
    @Column(name = "informacoes_uso", length = 255)
    private String usageInformation;

    /**
     * Tipo de cliente para o qual o cupom é aplicável.
     */
    @Column(name = "tipo_cliente", length = 50)
    private String customerType;

    /**
     * Valor mínimo do pedido para que o cupom seja aplicável.
     */
    @Column(name = "valor_minimo_pedido")
    private double minimumOrderValue;

    /**
     * Notas adicionais sobre o cupom.
     */
    @Column(name = "notas", length = 255)
    private String notes;

    /**
     * Data de criação do cupom.
     */
    @Column(name = "data_criacao")
    private LocalDate creationDate;

    /**
     * Código de barras ou código QR associado ao cupom.
     */
    @Column(name = "codigo_barras_qr", length = 100)
    private String barcodeOrQRCode;

    /**
     * Status de ativação do cupom.
     */
    @Column(name = "ativo")
    private int status;
}
