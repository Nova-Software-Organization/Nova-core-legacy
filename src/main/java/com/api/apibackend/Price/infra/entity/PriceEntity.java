package com.api.apibackend.Price.infra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.Product.Infra.entity.ProductEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Lazy
@Data
@Entity
@Table(name = "preco_produto")
@EqualsAndHashCode(of = "id")
public class PriceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpreco")
    private Long id;
    
    @Column(name = "preco")
    private BigDecimal price;

    @Column(name = "preco_de")
    private BigDecimal discountPrice;

    @Column(name = "data_inicio_vigencia")
    private Date startDate;

    @Column(name = "data_fim_vigencia")
    private Date endDate;

    @Column(name = "moeda")
    private String currency;

    @Column(name = "unidade_medida")
    private String unitOfMeasure;

    @Column(name = "Ativo")
    private int status;

    @Column(name = "tipo_desconto")
    private String discountType;

    @Column(name = "origem_preco")
    private String priceOrigin;

    @Column(name = "notas_observacoes")
    private String notes;

    @Column(name = "usuario_atualizacao")
    private String updatedBy;

    @OneToOne
    @JoinColumn(name = "idproduto")
    private ProductEntity productEntity;
}
