/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.shared.util;

import java.util.Objects;
import java.util.function.Consumer;

public class ValidationAreEqual {
    public <T> boolean areEqual(T currentValue, T newValue, Consumer<T> setter) {
        if (!Objects.equals(currentValue, newValue)) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }
}
