package com.ejercicio.block16springcloud.Backend.Exceptions;

public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
