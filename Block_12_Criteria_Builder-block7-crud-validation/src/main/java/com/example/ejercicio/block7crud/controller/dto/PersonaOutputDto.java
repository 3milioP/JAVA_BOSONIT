package com.example.ejercicio.block7crud.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDto {
    int id;
    String nombre;
    int edad;
    String poblacion;
}