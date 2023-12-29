package com.api.apibackend.PriceUnitary.Application.DTOs;

import java.math.BigDecimal;
import java.util.Date;

import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Unity.infra.persistence.entity.UnityEntity;

import lombok.Data;

@Data
public class PriceUnitaryDTO {
    private Long id;
    private ProductEntity productId;
    private UnityEntity unitId;
    private BigDecimal price;
    private Date startDate;
    private Date endDate;
}
