package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante;

import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutputSimpleDTO {
    String id_student;
    int id_persona;
    int num_hours_week;
    String coments;
    String branch;

}

