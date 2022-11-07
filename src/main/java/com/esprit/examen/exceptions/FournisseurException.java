package com.esprit.examen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FournisseurException extends RuntimeException {

    public FournisseurException(String message) {
        super(message);
    }
}
