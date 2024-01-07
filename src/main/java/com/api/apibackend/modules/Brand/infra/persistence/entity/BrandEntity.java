/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Brand.infra.persistence.entity;

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
@Table(name = "fornecedor_marca")
public class BrandEntity implements Serializable {
    
    /**
     * Idetificador unico da marca do fornecedor
     */
    @Id
    @Column(name = "idmarca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;
    
    /**
     * Nome da marca do fornecedor
     */
    @Column(name = "nome")
    private String name;
    
    /**
     * Url da imagem da marca de um fornecedor
     */
    @Column(name = "url")
    private String url;
}
