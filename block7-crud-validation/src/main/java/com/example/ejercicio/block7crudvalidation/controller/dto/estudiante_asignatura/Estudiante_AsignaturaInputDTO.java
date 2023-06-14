package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_AsignaturaInputDTO {
    int id_asignatura;
    List<Estudiante> estudiantes;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
}
