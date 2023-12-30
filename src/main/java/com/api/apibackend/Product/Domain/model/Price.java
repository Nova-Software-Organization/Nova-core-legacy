package com.api.apibackend.Product.Domain.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.Product.Infra.entity.ProductEntity;

import lombok.Data;

@Data
public class Price {
    private Long idPrice;
    private ProductEntity productEntity;
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

    public Price(Long idPrice, ProductEntity productEntity, BigDecimal price, BigDecimal discountPrice, Date startDate,
            Date endDate, String currency, String unitOfMeasure, int status, String discountType, String priceOrigin,
            String notes, String updatedBy) {
        this.idPrice = idPrice;
        this.productEntity = productEntity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currency = currency;
        this.unitOfMeasure = unitOfMeasure;
        this.status = status;
        this.discountType = discountType;
        this.priceOrigin = priceOrigin;
        this.notes = notes;
        this.updatedBy = updatedBy;
    }

    public Price() {
    }
}
