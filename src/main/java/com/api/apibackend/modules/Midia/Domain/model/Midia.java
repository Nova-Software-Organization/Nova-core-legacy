/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Midia.Domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Midia {
    private String name;
    private String url;
    private String description;
    private String price;
    private String category;
    private Date dateCreate;
}
