/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.ProductCategory.Application.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDTO {
    private String name;
    private int status;
    private String typeCategory;
}
