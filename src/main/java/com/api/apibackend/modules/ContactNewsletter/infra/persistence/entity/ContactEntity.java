/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ContactNewsletter.infra.persistence.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Lazy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Lazy
@Data
@Entity
@Table(name = "contato")
public class ContactEntity implements Serializable {

	private static final long serialVersionUID = 1L;

      /**
     * Identificador único do contato.
     */
    @Id
    @Column(name = "idcontato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontato;

    /**
     * Email do contato.
     */
    @NotBlank(message = "O email não pode estar em branco")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Column(name = "email")
    private String email;

    /**
     * Nome do contato.
     */
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @Column(name = "nome")
    private String name;

    /**
     * Número de telefone do contato.
     */
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter de 10 a 11 dígitos")
    @Column(name = "telefone")
    private String phone;

    /**
     * Status do contato.
     */
    @Column(name = "status")
    private Integer status;
}