package com.example.ejercicio.block7crudvalidation.controller.dto.profesor;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDTO {
    int id_professor;
    int persona;
    List<Estudiante> id_estudiante;
    String coments;
    String branch;
}
