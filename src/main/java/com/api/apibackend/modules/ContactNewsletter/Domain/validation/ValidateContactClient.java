/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ContactNewsletter.Domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Infra.validation.AuthenticationValidationServiceHandler;
import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ContactDTO;

@Service
public class ValidateContactClient {

    @Autowired
    private AuthenticationValidationServiceHandler clientValidationServiceHandler;

    public boolean validateContactHandler(ContactDTO contactRequest) {
        if (contactRequest != null) {
            boolean isValidEmail = clientValidationServiceHandler.isValidEmail(contactRequest.getEmail()) != null;

            if (isValidEmail) {
                return true;
            }
        }
        return false;
    }
}
