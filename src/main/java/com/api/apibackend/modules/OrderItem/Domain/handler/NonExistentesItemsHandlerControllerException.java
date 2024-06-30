/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.OrderItem.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.modules.OrderItem.Domain.exception.NonExistentesItemsException;

@ControllerAdvice
public class NonExistentesItemsHandlerControllerException {
    @ExceptionHandler(NonExistentesItemsException.class)
    public ResponseEntity<String> handleRegistrationFailedException(NonExistentesItemsException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
