package com.api.apibackend.modules.ProductCategory.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class ProductCategoryDTO {
    private String name;
    private int status;
    private String typeCategory;
}
