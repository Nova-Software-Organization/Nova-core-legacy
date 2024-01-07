/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Midia.Application.DTOs;

import java.util.Date;

import lombok.Data;

@Data
public class MidiaDTO {
    private Long id_image;
    private String url;
    private Date dateCreate;
    private String category;
}
