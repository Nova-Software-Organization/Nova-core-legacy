/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.shared.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class InputValidator<T> {

    /**
     * Verifica se a entrada fornecida é válida de acordo com a lógica específica da implementação.
     * @param input A entrada a ser validada.
     * @return true se a entrada for válida, false caso contrário.
     */
    public boolean isValidInput(T input) {
        if (input == null) {
            return false;
        }

        if (input instanceof String) {
            return isValidString((String) input);
        }
        
        if (input instanceof Collection) {
            return !((Collection<?>) input).isEmpty();
        }

        if (input instanceof Map) {
            return !((Map<?, ?>) input).isEmpty();
        }

        return true;
    }

    /**
     * Verifica se a string fornecida é válida de acordo com a lógica específica.
     * @param stringValue A string a ser validada.
     * @return true se a string for válida, false caso contrário.
     */
    private boolean isValidString(String stringValue) {
        String regex = "^[\\p{Alnum} ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringValue);
        return matcher.matches();
    }
}
