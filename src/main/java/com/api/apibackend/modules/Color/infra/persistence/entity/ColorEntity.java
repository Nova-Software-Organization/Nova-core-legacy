package com.api.apibackend.modules.Color.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "color", uniqueConstraints = {
    @UniqueConstraint(name = "color_hex_uindex", columnNames = {"hex"}),
    @UniqueConstraint(name = "color_name_uindex", columnNames = {"name"})
})
public class ColorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "nome")
  private String name;

  @Column(name = "hexadecimal")
  private String hex;
}