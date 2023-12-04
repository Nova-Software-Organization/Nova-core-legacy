package com.api.apibackend.States.Application.repository;

import org.springframework.http.ResponseEntity;

public interface IStateController {

	ResponseEntity<?> getStates();
}
