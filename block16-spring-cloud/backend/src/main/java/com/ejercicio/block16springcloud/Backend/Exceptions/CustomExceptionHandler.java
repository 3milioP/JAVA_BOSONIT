package com.ejercicio.block16springcloud.Backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomError customError = new CustomError();
        customError.setTimestamp(new Date());
        customError.setHttpCode(HttpStatus.NOT_FOUND.value());
        customError.setMensaje(ex.getMessage());
        return customError;
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomError handleUnprocessableEntityException(UnprocessableEntityException ex) {
        CustomError customError = new CustomError();
        customError.setTimestamp(new Date());
        customError.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        customError.setMensaje(ex.getMessage());
        return customError;
    }

}