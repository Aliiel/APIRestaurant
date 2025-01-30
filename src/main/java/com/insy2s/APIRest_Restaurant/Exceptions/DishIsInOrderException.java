package com.insy2s.APIRest_Restaurant.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DishIsInOrderException extends RuntimeException {

    public DishIsInOrderException(String message) {
        super(message);
    }
}
