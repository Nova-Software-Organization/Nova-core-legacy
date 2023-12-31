package com.api.apibackend.Price.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.math.BigDecimal;
import java.util.Date;

import com.api.apibackend.Product.Infra.entity.ProductEntity;

import lombok.Data;

@Data
public class PriceDTO {
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Date startDate;
    private Date endDate;
    private String currency;
    private String unitOfMeasure;
    private int status;
    private String discountType;
    private String priceOrigin;
    private String notes;
    private String updatedBy;
    private ProductEntity productEntity;
}