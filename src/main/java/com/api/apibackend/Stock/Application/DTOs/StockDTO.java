package com.api.apibackend.Stock.Application.DTOs;

import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Unity.infra.persistence.entity.UnityEntity;

import lombok.Data;

@Data
public class StockDTO {
    private Long idStock;
    private ProductEntity productEntity;
    private UnityEntity unityEntity;
    private int quantity;
}
