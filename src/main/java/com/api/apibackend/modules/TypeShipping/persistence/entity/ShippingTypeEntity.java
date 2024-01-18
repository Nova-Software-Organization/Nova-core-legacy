/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de tipo de frete dentro da empresa.
 */
package com.api.apibackend.modules.TypeShipping.persistence.entity;

import java.math.BigDecimal;
import java.util.List;

import com.api.apibackend.modules.DeliveryCompany.infra.persistence.entity.DeliveryCompanyEntity;
import com.api.apibackend.modules.Shipping.persistence.entity.ShippingEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.ws.rs.ext.ParamConverter.Lazy;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Lazy
@Data
@Entity
@Table(name = "tipo_entrega")
@EqualsAndHashCode(of = "idShippingType")
public class ShippingTypeEntity {
    
    /**
     * id (Primary key) tipo de entrega
     */
    @Id
    @Column(name = "id_tipo_entrega")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShippingType;

    /**
     * nome do tipo de entrega
     */
    @Column(name = "nome")
    private String name;

    /**
     * descrição do tipo de entrega
     */
    @Column(name = "descricao")
    private String description;

    /*
     * custo padrão do tipo de entrega
     */
    @Column(name = "custo_padrao")
    private BigDecimal standardCost;

    /**
     * estimativa de entrega
     */
    @Column(name = "estimativa_entrega")
    private Integer estimatedDeliveryTime;

    /**
     * serviço de entrega expressa
     */
    @Column(name = "servico_expresso")
    private Boolean expressService;

    /**
     * máximo de peso que pode ser entregue
     */
    @Column(name = "maximo_peso")
    private BigDecimal maximumWeight;

    /*
     * maximo de dimensões que podem ser entregue
     */
    @Column(name = "maximo_dimensoes")
    private BigDecimal maximumDimensions;

    /**
     * seguro da entrega
     */
    @Column(name = "seguro_disponivel")
    private Boolean insuranceAvailable;

    /**
     * codigo padrão da entrega
     */
    @Column(name = "codigo_rastreamento_padrao")
    private String defaultTrackingCode;

    /**
     * prioridade de entrega
     */
    @Column(name = "prioridade_servico")
    private Boolean priorityService;

    @ManyToOne
    @JoinColumn(name = "id_empresa_entrega", referencedColumnName = "id_empresa_entrega")
    private DeliveryCompanyEntity empresaEntrega;

    @OneToMany(mappedBy = "deliveryCompanyEntity", cascade = CascadeType.ALL)
    private List<ShippingEntity> shippings;
}
