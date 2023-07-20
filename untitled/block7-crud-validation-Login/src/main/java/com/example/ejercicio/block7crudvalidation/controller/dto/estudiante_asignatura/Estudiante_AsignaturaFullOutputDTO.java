package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_AsignaturaFullOutputDTO {
    int id_asignatura;
    List<Estudiante> estudiantes;
    String signature;
    String coments;
    Date initial_date;
    Date finish_date;
}
