package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Profesor")
public class Profesor {
    @Id
    @GeneratedValue
    int id_professor;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;
    @OneToMany(mappedBy = "id_profesor")
    List<Estudiante> id_estudiante;
    String coments;
    @NotNull
    String branch;


    public Profesor(ProfesorInputDTO profesorInputDTO, Persona persona) {
        this.persona = persona;
        id_estudiante = new ArrayList<>();
        this.coments = profesorInputDTO.getComents();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToProfesorOutputDTO(){
        return new ProfesorOutputDTO(
                this.id_professor,
                this.persona.getId_persona(),
                this.id_estudiante,
                this.coments,
                this.branch
        );
    }
}


