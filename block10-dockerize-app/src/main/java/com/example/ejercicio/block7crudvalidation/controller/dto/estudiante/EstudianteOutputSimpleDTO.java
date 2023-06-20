package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante;

import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante_Asignatura;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutputSimpleDTO {
    int id_student;
    int num_hours_week;
    String coments;
    String branch;

}

