package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_AsignaturaOutputDTO {
    String id_asignatura;
    Estudiante id_student;
    String signature;
    String coments;
    Date initial_date;
    Date finish_date;
}
