/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerRegistrationDate;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class CustomerRegistrationDateUseCase {
    private CustomerFilterService customerFilterService;

    public List<CustomerEntity> execute(String registrationDate) throws ParseException {
        return customerFilterService.findByRegistrationDate(registrationDate);
    }
}
