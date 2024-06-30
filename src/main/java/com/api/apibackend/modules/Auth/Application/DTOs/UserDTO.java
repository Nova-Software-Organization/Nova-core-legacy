/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.DTOs;

import java.util.Set;

import com.api.apibackend.modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<CustomGrantedAuthority> roles;
    private CustomerDTO customerDTO;
}
