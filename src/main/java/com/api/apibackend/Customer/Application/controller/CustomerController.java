package com.api.apibackend.Customer.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Application.useCase.CustomerDeactivateUseCase;
import com.api.apibackend.Customer.Application.useCase.CustomerDeleteUseCase;
import com.api.apibackend.Customer.Application.useCase.CustomerUpdateUseCase;
import com.api.apibackend.Customer.Domain.exception.ErrorEmptyCustomer;

@RestController
@RequestMapping("v1/cliente")
public class CustomerController {
    private CustomerUpdateUseCase customerUpdateUseCase;
    private CustomerDeleteUseCase customerDeleteUseCase;
    private CustomerDeactivateUseCase customerDeactivateUseCase;

    @Autowired
    public CustomerController(CustomerUpdateUseCase customerUpdateUseCase, CustomerDeleteUseCase customerDeleteUseCase, CustomerDeactivateUseCase customerDeactivateUseCase) {
        this.customerUpdateUseCase = customerUpdateUseCase;
        this.customerDeleteUseCase = customerDeleteUseCase;
        this.customerDeactivateUseCase = customerDeactivateUseCase;
    }

    @PostMapping(path = "/atualizar/{id}")
    public ResponseEntity<ResponseMessageDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID do cliente invalido", this.getClass().getName(), null));
            }
            
            if (customerDTO == null) {
                throw new ErrorEmptyCustomer("Erro: dados de cliente não fornecidos!");
            }
            
            return customerUpdateUseCase.execute(id, customerDTO, customerAddressDTO);
        } catch (ErrorEmptyCustomer e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Erro",this.getClass().getName(), e.getMessage()));
        }
    }

    @PostMapping(path = "/deletar/{id}")
    public ResponseEntity<ResponseMessageDTO> delete(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID do cliente invalido", this.getClass().getName(), null));
            }

            return customerDeleteUseCase.execute(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Erro",this.getClass().getName(), e.getMessage()));
        }
    }
}
