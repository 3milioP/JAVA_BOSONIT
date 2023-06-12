package com.example.ejercicio.block7crudvalidation.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }
}
