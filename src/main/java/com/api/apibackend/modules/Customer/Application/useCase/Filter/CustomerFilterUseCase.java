/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Application.useCase.Filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.apibackend.modules.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class CustomerFilterUseCase {
    private CustomerFilterService customerFilterService;

    @Autowired
    public CustomerFilterUseCase(CustomerFilterService customerFilterService) {
        this.customerFilterService = customerFilterService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String lastName) {

        List<CustomerEntity> filteredCustomers = customerFilterService.findCustomersByFiltersMain(name, cpf, lastName);

        if (filteredCustomers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageDTO("Nenhum cliente encontrado com os filtros fornecidos",
                            this.getClass().getName(), null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessageDTO("Clientes filtrados com sucesso", this.getClass().getName(), filteredCustomers, null));
    }
}
