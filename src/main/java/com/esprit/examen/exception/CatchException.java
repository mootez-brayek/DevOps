package com.esprit.examen.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CatchException extends RuntimeException{


    public CatchException(String message) {
        super(message);
    }
}
