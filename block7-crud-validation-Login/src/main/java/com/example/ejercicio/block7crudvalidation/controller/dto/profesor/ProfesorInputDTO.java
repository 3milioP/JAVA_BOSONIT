package com.example.ejercicio.block7crudvalidation.controller.dto.profesor;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDTO {
    int persona;
    List<Estudiante> id_estudiante;
    String coments;
    String branch;
}
