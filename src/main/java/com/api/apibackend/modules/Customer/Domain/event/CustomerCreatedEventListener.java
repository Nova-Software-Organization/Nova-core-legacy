/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.shared.Event.Domain.IDomainEventListener;

@Component
public class CustomerCreatedEventListener implements IDomainEventListener<CustomerCreated> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerCreatedEventListener(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @EventListener
    public void onEvent(CustomerCreated event) {
        Long createdClientId = event.getCreatedClientId();

        CustomerEntity customerEntity = customerRepository.findById(createdClientId).orElse(null);
        //chamar o serviço de envio de email após a criação do cliente
    }
}