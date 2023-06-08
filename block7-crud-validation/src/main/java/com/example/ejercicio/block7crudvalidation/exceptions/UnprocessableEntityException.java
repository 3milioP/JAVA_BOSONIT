package com.example.ejercicio.block7crudvalidation.exceptions;

public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String mensaje) {
        super(mensaje);
    }
}
