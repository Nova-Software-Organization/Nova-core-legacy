/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;

@Service
public class FindByRegistratonDateService {
    private CustomerRepository customerRepository;

    @Autowired
    public FindByRegistratonDateService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> findByRegistrationDate(String registrationDate) throws java.text.ParseException {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(registrationDate);
            return customerRepository.findByDateCreate(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Formato de data inválido. Use o formato yyyy-MM-dd.");
        }
    }
}
