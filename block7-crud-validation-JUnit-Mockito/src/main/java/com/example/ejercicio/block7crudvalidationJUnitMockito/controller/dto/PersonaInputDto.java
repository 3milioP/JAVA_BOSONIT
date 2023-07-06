package com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto {
    int id;
    String nombre;
    int edad;
    String poblacion;
}
