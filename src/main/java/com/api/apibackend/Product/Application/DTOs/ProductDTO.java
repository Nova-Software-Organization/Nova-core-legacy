package com.api.apibackend.Product.Application.DTOs;

import com.api.apibackend.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.Stock.Application.DTOs.StockDTO;
import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Unity.Application.DTOs.UnityDTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long idProduct;
    private MidiaDTO midia;
    private ProductCategoryDTO category;
    private String name;
    private SupplierDTO supplierEntity;
    private PriceDTO priceEntity;
    private StockDTO stockEntity;
    private UnityDTO unityEntity;
    private String description;
    private int status;
    private String sku;
}
