package com.example.ejercicio.block7crudvalidation.controller.dto.profesor;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDTO {
    int id_professor;
    int id_persona;
    List<Estudiante> id_estudiante;
    String coments;
    String branch;
}
