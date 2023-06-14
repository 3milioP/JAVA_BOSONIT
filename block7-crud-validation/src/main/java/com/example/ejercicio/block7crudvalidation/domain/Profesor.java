package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Profesor")
public class Profesor {
    @Id
    @GeneratedValue
    int id_professor;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona id_persona;
    @OneToMany(mappedBy = "id_profesor")
    List<Estudiante> id_estudiante;
    String coments;
    @NotNull
    String branch;


    public Profesor(ProfesorInputDTO profesorInputDTO) {
        this.coments = profesorInputDTO.getComents();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToProfesorOutputDTO(){
        return new ProfesorOutputDTO(
                this.id_professor,
                this.id_persona.getId_persona(),
                this.id_estudiante,
                this.coments,
                this.branch
        );
    }
}


