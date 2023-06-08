package com.example.ejercicio.block7crudvalidation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private Date timestamp;
    private int httpCode;
    private String mensaje;
}
