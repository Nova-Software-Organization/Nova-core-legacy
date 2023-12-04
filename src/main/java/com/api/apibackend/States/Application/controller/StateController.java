package com.api.apibackend.States.Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.States.Application.repository.IStateController;
import com.api.apibackend.States.Domain.model.State;
import com.api.apibackend.States.Domain.service.StateService;

@RestController
@RequestMapping("/v1")
public class StateController implements IStateController {
    
    @Autowired
    private StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/estados")
    public ResponseEntity<?> getStates() {
        try {
            List<State> states = stateService.getStates();
            return new ResponseEntity<>(states, HttpStatus.OK);
        } catch (IllegalAccessError ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}
