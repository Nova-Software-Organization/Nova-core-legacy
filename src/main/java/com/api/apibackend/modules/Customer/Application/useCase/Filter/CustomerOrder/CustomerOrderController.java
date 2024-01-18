/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@RestController
@RequestMapping("v1/cliente")
public class CustomerOrderController {
    private CustomerGetSortedByNameUseCase customerGetSortedByNameUseCase;

    @Autowired
    public CustomerOrderController(CustomerGetSortedByNameUseCase customerGetSortedByNameUseCase) {
        this.customerGetSortedByNameUseCase = customerGetSortedByNameUseCase;
    }
    

    @GetMapping("/ordenacao")
    public ResponseEntity<List<CustomerEntity>> getCustomersSortedByName(@RequestParam(name = "sort") String sort) {
        try {
            List<CustomerEntity> customers = customerGetSortedByNameUseCase.execute(sort);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
