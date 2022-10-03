package com.esprit.examen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReglementNotFoundException extends RuntimeException {
    public ReglementNotFoundException(String message) {
        super(message);
    }
}
