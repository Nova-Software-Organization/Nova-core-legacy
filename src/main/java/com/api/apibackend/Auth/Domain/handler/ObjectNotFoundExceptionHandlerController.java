package com.api.apibackend.Auth.Domain.handler;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.Auth.Domain.exception.ObjectNotFoundException;

@ControllerAdvice
public class ObjectNotFoundExceptionHandlerController {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleObjectNotFoundException(ObjectNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
