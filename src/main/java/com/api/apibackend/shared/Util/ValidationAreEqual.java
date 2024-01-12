package com.api.apibackend.shared.Util;

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
