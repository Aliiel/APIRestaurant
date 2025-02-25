package com.insy2s.APIRest_Restaurant.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DishAlreadyExistsException extends RuntimeException {

    public DishAlreadyExistsException(String message) {
        super(message);
    }
}
