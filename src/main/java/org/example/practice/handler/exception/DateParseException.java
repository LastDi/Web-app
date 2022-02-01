package org.example.practice.handler.exception;

import java.util.Map;

public class DateParseException extends RuntimeException{
    private Map<String, String> errorMessage;

    public DateParseException() {
    }

    /**
     * Custom exception to use when entity not found in DB
     */
    public DateParseException(String message) {
        super(message);
        this.errorMessage = Map.of("error", message);
    }

    public Map<String, String> getExcMessage() {
        return errorMessage;
    }
}
