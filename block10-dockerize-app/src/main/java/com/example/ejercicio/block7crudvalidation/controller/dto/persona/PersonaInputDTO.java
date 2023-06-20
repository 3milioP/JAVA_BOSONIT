package com.example.ejercicio.block7crudvalidation.controller.dto.persona;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Profesor;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDTO {
    Profesor id_profesor;
    Estudiante id_estudiante;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
}

