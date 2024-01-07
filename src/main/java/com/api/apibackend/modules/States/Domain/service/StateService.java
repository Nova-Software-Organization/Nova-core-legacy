package com.api.apibackend.modules.States.Domain.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.apibackend.modules.States.Application.controller.StateResponse;
import com.api.apibackend.modules.States.Domain.model.State;

@Service
public class StateService {

    private final String apiUrl = "https://www.geonames.org/childrenJSON?geonameId=3469034";

    @Autowired
    private RestTemplate restTemplate;

    public List<State> getStates() {
        ResponseEntity<StateResponse> response = restTemplate.getForEntity(apiUrl, StateResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            StateResponse stateResponse = response.getBody();
            if (stateResponse != null && stateResponse.getGeonames() != null) {
                return stateResponse.getGeonames();
            }
        }

        return Collections.emptyList();
    }
}
