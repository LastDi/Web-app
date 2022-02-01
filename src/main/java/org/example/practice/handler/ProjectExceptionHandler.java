package org.example.practice.handler;

import org.example.practice.handler.exception.DateParseException;
import org.example.practice.handler.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return new ResponseEntity<>(entityNotFoundException.getExcMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DateParseException.class)
    public ResponseEntity<?> entityNotFoundException(DateParseException dateParseException) {
        return new ResponseEntity<>(dateParseException.getExcMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public void nullPointerException(NullPointerException nullPointerException) {
        System.out.println(nullPointerException.getMessage()); // if you use Log, save this Exception to Log;
    }
}
