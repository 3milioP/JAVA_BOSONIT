package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteInputDTO {
    int person;
    int id_profesor;
    int num_hours_week;
    String coments;
    String branch;
    List<Integer> asignaturas;
}
