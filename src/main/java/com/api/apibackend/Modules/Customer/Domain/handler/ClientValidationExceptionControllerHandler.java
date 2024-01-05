/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.Modules.Customer.Domain.exception.ClientValidationException;

@ControllerAdvice
public class ClientValidationExceptionControllerHandler {
    @ExceptionHandler(ClientValidationException.class)
    public ResponseEntity<String> handleValidationNotFoundException(ClientValidationException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
