package com.api.apibackend.Product.Domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class Product implements Serializable {
    @JsonAlias("supplier")
    private Supplier supplier;

    @JsonAlias("price")
    private Price price;

    private Long id;
    private String name;
    private String url;
    private String description;
    private Date date_create;
    private String category;
    private int quantityInStock;
    private int status;

    public Product(
            Long idProduct,
            String name,
            String url,
            String description,
            String nameCompany,
            int quantityInStock,
            int status,
            Supplier supplier,
            Price price) {
    }

    public Product(Long idProduct,
            String name2,
            String url2,
            String description2,
            String name3,
            Double price,
            Double dePrice,
            int quantityInStock2) {
    }

    public Product() {
    }
}
