package com.api.apibackend.States.Domain.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.apibackend.States.Application.controller.StateResponse;
import com.api.apibackend.States.Domain.model.State;

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
