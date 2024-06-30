/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Empresa entrega dentro da empresa.
 */
package com.api.apibackend.modules.DeliveryCompany.infra.persistence.entity;

import java.util.List;

import com.api.apibackend.modules.DeliveryService.infra.persistence.entity.DeliveryServiceEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "empresa_entrega")
@EqualsAndHashCode(of = "idDeliveryCompany")
public class DeliveryCompanyEntity {

    /**
     * id (primary key) empresa de entrega
     */
    @Id
    @Column(name = "id_empresa_entrega")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeliveryCompany;

    /**
     * nome da pessoa
     */
    @Column(name = "nome")
    private String name;

    /**
     * pessoa para contato dentro da empresa
     */
    @Column(name = "pessoa_contato")
    private String contactPerson;
    
    /**
     * email de contato da empresa
     */
    @Column(name = "email_contato")
    private String contactEmail;

    /**
     * telefone de contato da empresa
     */
    @Column(name = "telefone_contato")
    private String contactPhone;

    /**
     * site da empresa que fornece o serviço de entrega
     */
    @Column(name = "site")
    private String website;

    @OneToMany(mappedBy = "deliveryCompanyEntity", cascade = CascadeType.ALL)
    private List<DeliveryServiceEntity> deliveryServiceEntities;
}