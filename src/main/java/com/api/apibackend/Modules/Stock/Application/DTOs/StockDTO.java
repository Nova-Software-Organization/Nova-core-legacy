package com.api.apibackend.Modules.Stock.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import com.api.apibackend.Modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Modules.Unity.infra.persistence.entity.UnityEntity;

import lombok.Data;

@Data
public class StockDTO {
    private Long idStock;
    private ProductEntity productEntity;
    private UnityEntity unityEntity;
    private int quantity;
}
