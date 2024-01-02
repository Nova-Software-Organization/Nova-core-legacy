/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Application.DTOs.registration;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDTO {
    private Boolean isAdmin;
    private String name;
    private String lastName;
    private String cpf;
    private String phone;
    private int age;
    private String gender;
    private String email;
    private String password;
    private Date birthDate;
    private Date dateCreate;
}
