/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de entrega de um pedido dentro da empresa.
 */
package com.api.apibackend.modules.Delivery.infra.persistence.entity;

import java.time.LocalDateTime;

import com.api.apibackend.modules.Shipping.persistence.entity.ShippingEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "entrega")
@EqualsAndHashCode(of = "idDelivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long idDelivery;

    @ManyToOne
    @JoinColumn(name = "idFrete")
    private ShippingEntity shipping;

    /**
     * data de envio da entrega
     */
    @Column(name = "data_envio")
    private LocalDateTime shippingDate;

    /**
     * data estimada de entrega
     */
    @Column(name = "data_estimada_entrega")
    private LocalDateTime estimatedDeliveryDate;

    /**
     * localização atual de entrega
     */
    @Column(name = "localizacao_atual")
    private String currentLocation;

    /**
     * entrega confirmada
     */
    @Column(name = "entrega_confirmada")
    private Boolean delivered;

    /**
     * Observações de entrega
     */
    @Column(name = "observacoes_entrega")
    private String deliveryObservations;
}
