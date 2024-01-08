/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.CustomerAddress.Domain.service;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

@Service
public class CustomerAddressOrderService {
    
    public OrderAddressEntity createAddressOrder(CustomerAddressRequest customerAddress) {
        if (customerAddress == null) {
            throw new IllegalArgumentException("endereço não existe, está vazio");
        }

        OrderAddressEntity addressEntity = new OrderAddressEntity();
        addressEntity.setRoad(customerAddress.getRoad());
        addressEntity.setNeighborhood(customerAddress.getNeighborhood());
        addressEntity.setHousenumber(customerAddress.getHousenumber());
        addressEntity.setState(customerAddress.getState());
        addressEntity.setCep(customerAddress.getCep());
        
        return addressEntity;
    }
}
