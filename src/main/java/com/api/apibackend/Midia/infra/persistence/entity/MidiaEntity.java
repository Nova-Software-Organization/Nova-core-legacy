package com.api.apibackend.Midia.infra.persistence.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de midia do produto dentro da empresa.
 */

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa uma entidade de mídia no sistema.
 */
@Lazy
@Data
@Entity
@Table(name = "midia")
@EqualsAndHashCode(of = "id")
public class MidiaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único da mídia.
     */
    @Id
    @Column(name = "idmid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_image;

    /**
     * URL da mídia.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Data de criação da mídia.
     */
    @Column(name = "data_criacao")
    private Date dateCreate;

    /**
     * Categoria da mídia.
     */
    @Column(name = "categoria", nullable = false)
    private String category;
}
