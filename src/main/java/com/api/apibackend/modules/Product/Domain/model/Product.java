/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;


@Data
public class Product implements Serializable {
    @JsonAlias("supplier")
    private Supplier supplier;

    @JsonAlias("price")
    private Price price;

    @JsonAlias("price")
    private StockEntity stockEntity;
    private Long id;
    private String name;
    private String url;
    private String description;
    private Date dateCreate;
    private String category;
    private int status;

    public Product(
            Long idProduct,
            String name,
            String url,
            String description,
            String nameCompany,
            int quantity,
            int status) {
    }

    public Product(Long idProduct,
            String name2,
            String url2,
            String description2,
            String name3,
            BigDecimal price,
            BigDecimal dePrice,
            int quantity) {
    }

    public Product() {
    }
}
