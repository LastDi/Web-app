package org.example.practice.handler.exception;

import java.util.Map;

public class EntityNotFoundException extends RuntimeException{
    private Map<String, String> errorMessage;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
        this.errorMessage = Map.of("error", message);
    }

    public Map<String, String> getExcMessage() {
        return errorMessage;
    }
}
