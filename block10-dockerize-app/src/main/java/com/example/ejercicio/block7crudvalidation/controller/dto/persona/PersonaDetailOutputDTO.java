package com.example.ejercicio.block7crudvalidation.controller.dto.persona;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDetailOutputDTO extends PersonaOutputDTO {
    private int id_persona;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private ProfesorOutputDTO profesor;
    private EstudianteOutputSimpleDTO estudiante;
}

