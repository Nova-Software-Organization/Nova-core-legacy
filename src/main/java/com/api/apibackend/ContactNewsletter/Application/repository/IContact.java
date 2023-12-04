package com.api.apibackend.ContactNewsletter.Application.repository;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.ContactNewsletter.Application.controller.ContactRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface IContact {
    ResponseEntity<?> toReceiveContact(@RequestBody ContactRequest contactRequest);
}
