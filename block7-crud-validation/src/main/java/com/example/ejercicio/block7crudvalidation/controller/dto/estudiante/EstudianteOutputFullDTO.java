package com.example.ejercicio.block7crudvalidation.controller.dto.estudiante;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante_Asignatura;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.domain.Profesor;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutputFullDTO extends EstudianteOutputSimpleDTO{
    int id_student;
    int persona;
    List<Estudiante_Asignatura> estudianteAsignaturas;
    int num_hours_week;
    String coments;
    int id_profesor;
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
