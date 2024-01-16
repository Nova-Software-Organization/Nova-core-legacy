/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de Preço de Produto no sistema.
 */
package com.api.apibackend.modules.Price.infra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Lazy;

import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

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

/**
 * Representa uma entidade de Preço de Produto no sistema.
 */
@Lazy
@Data
@Entity
@Table(name = "preco_produto")
@EqualsAndHashCode(of = "id")
public class PriceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do preço.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpreco")
    private Long id;

    /**
     * Valor do preço.
     */
    @Column(name = "preco")
    private BigDecimal price;

    /**
     * Valor do preço com desconto.
     */
    @Column(name = "preco_de")
    private BigDecimal discountPrice;

    /**
     * Data de início da vigência do preço.
     */
    @Column(name = "data_inicio_vigencia")
    private Date startDate;

    /**
     * Data de término da vigência do preço.
     */
    @Column(name = "data_fim_vigencia")
    private Date endDate;

    /**
     * Moeda utilizada para o preço.
     */
    @Column(name = "moeda")
    private String currency;

    /**
     * Unidade de medida associada ao preço.
     */
    @Column(name = "unidade_medida")
    private String unitOfMeasure;

    /**
     * Status de ativação do preço.
     */
    @Column(name = "ativo")
    private int status;

    /**
     * Tipo de desconto aplicado ao preço.
     */
    @Column(name = "tipo_desconto")
    private String discountType;

    /**
     * Origem do preço.
     */
    @Column(name = "origem_preco")
    private String priceOrigin;

    /**
     * Notas ou observações relacionadas ao preço.
     */
    @Column(name = "notas_observacoes")
    private String notes;

    /**
     * Usuário responsável pela última atualização do preço.
     */
    @Column(name = "usuario_atualizacao")
    private String updatedBy;

    /**
     * Entidade de produto associada ao preço.
     */
    @OneToOne
    @JoinColumn(name = "idproduto")
    private ProductEntity productEntity;
}
