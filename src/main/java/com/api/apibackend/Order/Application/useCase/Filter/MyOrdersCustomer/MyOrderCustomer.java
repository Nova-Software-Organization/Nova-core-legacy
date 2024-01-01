/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Order.Application.useCase.Filter.MyOrdersCustomer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Customer.Domain.exception.ClientNotFoundException;
import com.api.apibackend.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.Customer.Domain.service.MyRequestCustomerService;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/pedido")
public class MyOrderCustomer {
    private CustomerFilterService clientSearchService;
    private MyRequestCustomerService responseBuilderService;

    @Autowired
    public MyOrderCustomer(
            CustomerFilterService customerSearchService,
            MyRequestCustomerService myRequestCustomerService) {
        this.clientSearchService = clientSearchService;
        this.responseBuilderService = responseBuilderService;
    }

    @GetMapping("/cliente/{email}")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Busca os pedidos do usuario pelo email", description = "Responsavel por buscar os pedidos de um usuario pelo email e retornar os dados")
    @Operation(summary = "Rota responsavel por busca os pedidos de um cliente pelo email informado!")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            Optional<CustomerEntity> client = clientSearchService.searchClientByEmail(email);
            if (client != null) {
                return responseBuilderService.buildUserResponse(client);
            }

            return ResponseEntity.notFound().build();
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
    }
}
