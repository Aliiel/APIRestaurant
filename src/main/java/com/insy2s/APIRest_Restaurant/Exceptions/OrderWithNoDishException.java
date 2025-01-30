package com.insy2s.APIRest_Restaurant.Exceptions;

public class OrderWithNoDishException extends RuntimeException {

    public OrderWithNoDishException(String message) {
        super(message);
    }
}
