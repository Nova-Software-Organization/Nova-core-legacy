package com.api.apibackend.ProductCategory.infra.persistence.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Lazy
@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "produto_categoria")
public class ProductCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long id;

    @Column(name = "nome")
    private String name;
}
