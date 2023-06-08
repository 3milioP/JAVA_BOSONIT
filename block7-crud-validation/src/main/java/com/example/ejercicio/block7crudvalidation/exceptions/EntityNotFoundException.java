package com.example.ejercicio.block7crudvalidation.exceptions;

import java.util.NoSuchElementException;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }
}
