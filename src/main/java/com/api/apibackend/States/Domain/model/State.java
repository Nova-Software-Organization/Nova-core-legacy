package com.api.apibackend.States.Domain.model;

import lombok.Data;

@Data
public class State {
    
    private Long id;
    
    private String adminCode1;
    
    private String lng;
    
    private String geonameId;
    
    private String toponymName;
    
    private String countryId;
}
