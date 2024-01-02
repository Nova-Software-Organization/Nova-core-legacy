package com.api.apibackend.modules.Stock.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.modules.Unity.infra.persistence.entity.UnityEntity;

import lombok.Data;

@Data
public class StockDTO {
    private Long idStock;
    private ProductEntity productEntity;
    private UnityEntity unityEntity;
    private int quantity;
}
