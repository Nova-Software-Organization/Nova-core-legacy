package com.api.apibackend.modules.ProductCategory.infra.persistence.entity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Produto dentro da categoria.
 */

import java.io.Serializable;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Lazy
@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "produto_categoria")
public class ProductCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador único da categoria de produto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long id;

    /**
     * Nome da categoria de produto.
     */
    @Column(name = "nome")
    private String name;

    /**
     * Status de ativação da categoria de produto.
     */
    @Column(name = "status")
    private int status;

    /**
     * Tipo da categoria de produto.
     */
    @Column(name = "tipo_categoria")
    private String typeCategory;
}
