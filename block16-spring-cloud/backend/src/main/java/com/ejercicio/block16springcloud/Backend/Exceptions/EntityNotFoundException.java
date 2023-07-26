package com.ejercicio.block16springcloud.Backend.Exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
