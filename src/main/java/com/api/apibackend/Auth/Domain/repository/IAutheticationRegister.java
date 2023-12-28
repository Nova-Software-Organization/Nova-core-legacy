package com.api.apibackend.Auth.Domain.repository;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;

public interface IAutheticationRegister {
    ResponseEntity<String> registerUserWithSeparateData(CustomerDTO customerDTO, CustomerAddressDTO CustomerAddressDTO);
    Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles);
}
