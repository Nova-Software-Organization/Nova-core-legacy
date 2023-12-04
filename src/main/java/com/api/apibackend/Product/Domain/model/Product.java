package com.api.apibackend.Product.Domain.model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private Long id;

    private String name;
    
    private String url;
    
    private String description;
    
    private Date date_create;
    
    private String category;
    
    private Double price;
    
    private Double dePrice;
    
    private int quantityInStock;

    public Product() { }

    public Product(
        Long id,
        String name,
        String url,
        String description,
        Date date_create,
        String category,
        Double price,
        Double dePrice,
        int quantityInStock
    ) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.date_create = date_create;
        this.category = category;
        this.price = price;
        this.dePrice = dePrice;
        this.quantityInStock = quantityInStock;
    }

    public Product(
        Long id,
        String name,
        String url,
        String description,
        String category,
        Double price,
        Double dePrice,
        int quantityInStock
    ) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.category = category;
        this.price = price;
        this.dePrice = dePrice;
        this.quantityInStock = quantityInStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Double getDePrice() {return dePrice;}

    public void setDePrice(Double dePrice) {this.dePrice = dePrice;}
}
