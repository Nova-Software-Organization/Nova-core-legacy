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

import org.springframework.stereotype.Component;

@Component
public class DataProcessor<T> {

    /**
     * Realiza uma operação de limpeza ou transformação nos dados fornecidos.
     * @param data Os dados a serem limpos ou transformados.
     * @return Os dados após a operação.
     */
    public T process(T data) {
        if (data == null) {
            return null;
        }

        if (data instanceof String) {
            return (T) cleanString((String) data);
        }
        
        if (data instanceof Collection) {
            ((Collection<?>) data).removeIf(elt -> elt == null);
            return data;
        }
        
        if (data instanceof Map) {
            ((Map<?, ?>) data).entrySet().removeIf(entry -> entry.getKey() == null || entry.getValue() == null);
            return data;
        }

        return data;
    }

    /**
     * Remove espaços em branco no início e no final da string.
     * @param stringValue A string a ser limpa.
     * @return A string após a operação.
     */
    private String cleanString(String stringValue) {
        return stringValue.trim();
    }
}