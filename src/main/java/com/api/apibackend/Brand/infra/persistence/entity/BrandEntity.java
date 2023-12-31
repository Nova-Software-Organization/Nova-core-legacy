package com.api.apibackend.Brand.infra.persistence.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "marca")
public class BrandEntity implements Serializable {

    @Id
    @Column(name = "idmarca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;

    @Column(name = "nome")
    private String name;

    @Column(name = "url")
    private String url;
}
