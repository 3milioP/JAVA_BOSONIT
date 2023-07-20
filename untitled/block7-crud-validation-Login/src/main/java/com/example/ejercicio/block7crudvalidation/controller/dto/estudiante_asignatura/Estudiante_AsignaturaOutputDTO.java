package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_AsignaturaOutputDTO {
    int id_asignatura;
    String signature;
    String coments;
    Date initial_date;
    Date finish_date;
}
