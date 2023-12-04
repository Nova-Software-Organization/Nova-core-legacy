package com.api.apibackend.Midia.infra.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "midia")
@EqualsAndHashCode(of = "id")
public class MidiaEntity implements Serializable {
    
    @Id
    @Column(name = "idmid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_image;

    @Column(name = "url")
    private String url;

    @Column(name = "data_criacao")
    private Date date_create;

    @Column(name = "categoria")
    private String category;
}
