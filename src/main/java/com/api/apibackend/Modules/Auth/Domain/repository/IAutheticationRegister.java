/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Auth.Domain.repository;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerDTO;

public interface IAutheticationRegister {
    ResponseEntity<ResponseMessageDTO> registerUserWithSeparateData(CustomerDTO customerDTO,
            CustomerAddressDTO CustomerAddressDTO);

    Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles);
}
