package com.api.apibackend.Product.Domain.model;

import java.util.Date;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;

@Lazy
@Data
public class Price {
    private Long idPrice;
    private ProductEntity productEntity;
    private Double price;
    private Double discountPrice;
    private Date startDate;
    private Date endDate;
    private String currency;
    private String unitOfMeasure;
    private String status;
    private String discountType;
    private String priceOrigin;
    private String notes;
    private String updatedBy;

    @JsonCreator
    public Price(Long idPrice, ProductEntity productEntity, Double price, Double discountPrice, Date startDate, Date endDate, String currency, String unitOfMeasure, String status, String discountType, String priceOrigin, String notes, String updatedBy) {
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
}
