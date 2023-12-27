package com.api.apibackend.Order.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Customer.Domain.exception.ClientNotFoundException;
import com.api.apibackend.Customer.Domain.service.CustomerSearchService;
import com.api.apibackend.Customer.Domain.service.MyRequestCustomerService;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/conta")
public class MyOrdersAccountController {
    private CustomerSearchService clientSearchService;
    private MyRequestCustomerService responseBuilderService;
    
    @Autowired
    public MyOrdersAccountController (
        CustomerSearchService customerSearchService,
        MyRequestCustomerService myRequestCustomerService
    ) {
        this.clientSearchService = clientSearchService;
        this.responseBuilderService = responseBuilderService;
    }

    @GetMapping("/pedidos")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Busca os pedidos do usuario pelo email", description = "Responsavel por buscar os pedidos de um usuario pelo email e retornar os dados")
    @Operation(summary = "Rota responsavel por busca os pedidos de um cliente pelo email informado!")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        try {
            CustomerEntity client = clientSearchService.searchClientByEmail(email);
            if (client != null) {
                return responseBuilderService.buildUserResponse(client);
            }

            return ResponseEntity.notFound().build();
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
    }
}
