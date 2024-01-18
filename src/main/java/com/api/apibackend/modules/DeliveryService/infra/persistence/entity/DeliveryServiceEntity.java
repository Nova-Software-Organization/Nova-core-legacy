/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Serviço entrega dentro da empresa.
 */
package com.api.apibackend.modules.DeliveryService.infra.persistence.entity;

import java.math.BigDecimal;

import com.api.apibackend.modules.DeliveryCompany.infra.persistence.entity.DeliveryCompanyEntity;

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
@Table(name = "servico_entrega")
@EqualsAndHashCode(of = "idDeliveryService")
public class DeliveryServiceEntity {

    /*
     * id (primary key) serviço de entrega
     */
    @Id
    @Column(name = "id_servico_entrega")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeliveryService;

    /**
     * nome do serviço de entrega
     */
    @Column(name = "nome_servico")
    private String serviceName;

    /**
     * descrição
     */
    @Column(name = "descricao")
    private String description;

    /**
     * custo do servico
     */
    @Column(name = "custo")
    private BigDecimal cost;

    /**
     * tempo de entrega estimado
     */
    @Column(name = "tempo_entrega_estimado")
    private Integer estimatedDeliveryTime;

    @ManyToOne
    @JoinColumn(name = "id_empresa_entrega")
    private DeliveryCompanyEntity deliveryCompanyEntity;
}
