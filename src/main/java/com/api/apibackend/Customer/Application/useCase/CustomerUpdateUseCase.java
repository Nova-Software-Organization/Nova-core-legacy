package com.api.apibackend.Customer.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Domain.exception.ErrorEmptyCustomer;
import com.api.apibackend.Customer.Domain.service.CustomerServiceImp;

public class CustomerUpdateUseCase {
    private CustomerServiceImp customerServiceImp;

    @Autowired
    public CustomerUpdateUseCase(CustomerServiceImp customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

     public ResponseEntity<ResponseMessageDTO> execute(Long id, CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
        try {
            if (customerDTO == null) {
                throw new ErrorEmptyCustomer("Erro: dados do cliente não fornecidos!");
            }
            
            return customerServiceImp.update(id, customerDTO, customerAddressDTO);
        } catch (ErrorEmptyCustomer e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Erro", this.getClass().getName(), e.getMessage()));
        }
    }
}
