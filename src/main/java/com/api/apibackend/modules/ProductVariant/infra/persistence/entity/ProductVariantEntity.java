package com.api.apibackend.modules.ProductVariant.infra.persistence.entity;

import com.api.apibackend.modules.Color.infra.persistence.entity.ColorEntity;
import com.api.apibackend.modules.Product.Domain.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Produto_variante")
public class ProductVariantEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "color_id")
  private ColorEntity color;

  @Column(name = "width")
  private String width;

  @Column(name = "height")
  private String height;

  @Column(name = "composition")
  private String composition;

  @Column(name = "price")
  private Float price;

  @Column(name = "cargo_price")
  private Float cargoPrice;

  @Column(name = "tax_percent")
  private Float taxPercent;

  @Column(name = "image")
  private String image;

  @Column(name = "thumb")
  private String thumb;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "sell_count")
  private Integer sellCount;

  @Column(name = "live")
  private Integer live;
}