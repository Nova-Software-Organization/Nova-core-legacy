/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerGetWithPagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.apibackend.modules.Customer.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

public class CustomerGetWithPaginationController {
    private CustomerGetWithPaginationUseCase customerGetWithPaginationUseCase;

    @Autowired
    public CustomerGetWithPaginationController(CustomerGetWithPaginationUseCase customerGetWithPaginationUseCase) {
        this.customerGetWithPaginationUseCase = customerGetWithPaginationUseCase;
    }

    @GetMapping("/paginacao")
    public ResponseEntity<ResponseMessageDTO> getCustomersWithPagination(
            @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        try {
            Page<CustomerEntity> customers = customerGetWithPaginationUseCase.getCustomersWithPagination(page, size);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Sucesso", this.getClass().getName(), null, null, customers));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }
}
