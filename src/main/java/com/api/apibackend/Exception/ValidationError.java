package com.api.apibackend.Exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ValidationError {
    private String field;
    private String message;

    public ValidationError(String field, String message) { }
}
