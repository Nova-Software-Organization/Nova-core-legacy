/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de frete que esta associada a varias entidades dentro da empresa.
 */
package com.api.apibackend.modules.Shipping.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.api.apibackend.modules.DeliveryCompany.infra.persistence.entity.DeliveryCompanyEntity;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.TypeShipping.persistence.entity.ShippingTypeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.ws.rs.ext.ParamConverter.Lazy;
import lombok.EqualsAndHashCode;

@Lazy
@Entity
@Table(name = "frete")
@EqualsAndHashCode(of = "idFrete")
public class ShippingEntity {
    
    @Id
    @Column(name = "idFrete")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShipping;

    @ManyToOne
    @JoinColumn(name = "id_tipo_entrega", referencedColumnName = "id_tipo_entrega")
    private ShippingTypeEntity shippingTypeEntity;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "id_empresa_entrega", referencedColumnName = "id_empresa_entrega")
    private DeliveryCompanyEntity deliveryCompanyEntity;

    /**
     * Custo de envio do pedido
     */
    private BigDecimal shippingCost;

    /**
     * Data de envio do pedido
     */
    private LocalDateTime shippingDate;

    /**
     * Estimativa de entrega do pedido
     */
    private LocalDateTime estimatedDeliveryDate;

    /*
     * localização de entrega do pedido
     */
    private String currentLocation;

    /*
     * confirmação de entrega do pedido
     */
    private Boolean delivered;

    /**
     * observações de entrega
     */
    private String observations;
}
