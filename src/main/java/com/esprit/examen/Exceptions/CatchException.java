package com.esprit.examen.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Data;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CatchException extends RuntimeException{


    public CatchException(String message) {
        super(message);
    }
}
