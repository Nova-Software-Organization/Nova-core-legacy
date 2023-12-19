package com.api.apibackend.Price.infra.entity;

import java.io.Serializable;
import java.util.Date;

import com.api.apibackend.Product.Infra.entity.ProductEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "preco_produto")
@EqualsAndHashCode(of = "idPrice")
public class PriceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idpreco")
    private Long idPrice;

    @OneToOne
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    private ProductEntity productEntity;
    
    @Column(name = "preco")
    private Double price;

    @Column(name = "preco_de")
    private Double discountPrice;

    @Column(name = "data_inicio_vigencia")
    private Date startDate;

    @Column(name = "data_fim_vigencia")
    private Date endDate;

    @Column(name = "moeda")
    private String currency;

    @Column(name = "unidade_medida")
    private String unitOfMeasure;

    @Column(name = "status")
    private String status;

    @Column(name = "tipo_desconto")
    private String discountType;

    @Column(name = "origem_preco")
    private String priceOrigin;

    @Column(name = "notas_observacoes")
    private String notes;

    @Column(name = "usuario_atualizacao")
    private String updatedBy;
}
