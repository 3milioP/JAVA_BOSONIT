package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutputFullDTO extends EstudianteOutputSimpleDTO{
    String id_student;
    int id_persona;
    int num_hours_week;
    String coments;
    String branch;
    String usuario;
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
