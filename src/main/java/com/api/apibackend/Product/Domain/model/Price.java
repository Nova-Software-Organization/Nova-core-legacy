package com.api.apibackend.Product.Domain.model;

import java.util.Date;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.Product.Infra.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Lazy
@Data
@AllArgsConstructor
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
}
