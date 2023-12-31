package com.api.apibackend.Customer.Application.controller.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Application.useCase.CustomerFilterUseCase;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

@RestController
@RequestMapping("v1/cliente")
public class CustomerFilterController {
    private final CustomerFilterUseCase customerFilterUseCase;

    @Autowired
    public CustomerFilterController(CustomerFilterUseCase customerFilterUseCase) {
        this.customerFilterUseCase = customerFilterUseCase;
    }

    @GetMapping("/filtros/{cpf}/{name}/{lastname}")
    public ResponseEntity<ResponseMessageDTO> filterByFiltersMain(
            @PathVariable(required = false) String name,
            @PathVariable(required = false) String cpf,
            @PathVariable(required = false) String lastname) {
        return customerFilterUseCase.execute(name, cpf, lastname);
    }
}
