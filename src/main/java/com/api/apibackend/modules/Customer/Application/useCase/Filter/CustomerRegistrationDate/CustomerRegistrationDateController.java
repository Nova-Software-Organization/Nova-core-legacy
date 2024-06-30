/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerRegistrationDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Customer.Domain.exception.CustomerNotFoundException;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@RestController
@RequestMapping("v1/cliente")
public class CustomerRegistrationDateController {
    private CustomerRegistrationDateUseCase customerRegistrationDateUseCase;

    @Autowired
    public CustomerRegistrationDateController(CustomerRegistrationDateUseCase customerRegistrationDateUseCase) {
        this.customerRegistrationDateUseCase = customerRegistrationDateUseCase;
    }

    @GetMapping("/pesquisarPorDataRegistro")
    public ResponseEntity<List<CustomerEntity>> searchCustomersByRegistrationDate(
            @RequestParam(name = "dataRegistro") String registrationDate) {
        try {
            List<CustomerEntity> customers = customerRegistrationDateUseCase.execute(registrationDate);
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
