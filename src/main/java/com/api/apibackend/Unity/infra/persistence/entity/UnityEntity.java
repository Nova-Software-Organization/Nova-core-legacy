package com.api.apibackend.Unity.infra.persistence.entity;

import java.io.Serializable;

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
@Table(name = "unidade")
@EqualsAndHashCode(of = "idunity")
public class UnityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idunidade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnity;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "abreviacao", nullable = false)
    private String abbreviation;
}
