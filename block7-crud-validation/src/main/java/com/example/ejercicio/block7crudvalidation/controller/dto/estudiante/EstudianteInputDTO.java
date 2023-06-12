package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante;

import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteInputDTO {
    int id_persona;
    int num_hours_week;
    String coments;
    String branch;
}
