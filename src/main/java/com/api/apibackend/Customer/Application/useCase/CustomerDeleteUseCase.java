package com.api.apibackend.Customer.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Domain.service.CustomerServiceImp;

@Service
public class CustomerDeleteUseCase {
    private CustomerServiceImp customerServiceImp;

    @Autowired
    public CustomerDeleteUseCase(CustomerServiceImp customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        return customerServiceImp.delete(id);
    }
}
