package com.example.ejercicio.block7crudvalidation.controller.dto.profesor;

import com.example.ejercicio.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDTO {
    String id_professor;
    Persona id_persona;
    String coments;
    String branch;
}
