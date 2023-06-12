package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "Profesor")
public class Profesor {
    @Id
    @GeneratedValue
    String id_professor;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona id_persona;
    String coments;
    @NotNull
    String branch;


    public Profesor(ProfesorInputDTO profesorInputDTO) {
        this.id_persona = profesorInputDTO.getId_persona();
        this.coments = profesorInputDTO.getComents();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToProfesorOutputDTO(){
        return new ProfesorOutputDTO(
                this.id_professor,
                this.id_persona,
                this.coments,
                this.branch
        );
    }
}


