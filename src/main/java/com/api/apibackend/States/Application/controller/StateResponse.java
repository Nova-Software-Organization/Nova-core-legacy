package com.api.apibackend.States.Application.controller;

import java.util.List;

import com.api.apibackend.States.Domain.model.State;

public class StateResponse {

    private List<State> geonames;

    public List<State> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<State> geonames) {
        this.geonames = geonames;
    }
}