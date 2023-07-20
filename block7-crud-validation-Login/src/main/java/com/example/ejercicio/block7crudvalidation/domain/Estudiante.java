package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputFullDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue
    int id_student;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona person;
    @ManyToMany
    List<Estudiante_Asignatura> estudianteAsignaturas;
    @NotNull
    int num_hours_week;
    String coments;
    @OneToOne
    @JoinColumn(name = "profesor")
    Profesor id_profesor;
    @NotNull
    String branch;

    public Estudiante(EstudianteInputDTO estudianteInputDTO, Persona persona, Profesor profesor) {
        this.person = persona;
        this.id_profesor = profesor;
        this.num_hours_week = estudianteInputDTO.getNum_hours_week();
        this.coments = estudianteInputDTO.getComents();
        this.branch = estudianteInputDTO.getBranch();
        estudianteAsignaturas = new ArrayList<>();
    }

    public EstudianteOutputSimpleDTO estudianteToEstudianteOutputDTO(String type) {
        if (type.equals("full")) {
            return new EstudianteOutputFullDTO(
                    this.id_student,
                    this.person.getId_persona(),
                    this.estudianteAsignaturas,
                    this.num_hours_week,
                    this.coments,
                    this.id_profesor.getId_professor(),
                    this.branch,
                    this.person.getUsuario(),
                    this.person.getName(),
                    this.person.getSurname(),
                    this.person.getCompany_email(),
                    this.person.getPersonal_email(),
                    this.person.getCity(),
                    this.person.getActive(),
                    this.person.getCreated_date(),
                    this.person.getImagen_url(),
                    this.person.getTermination_date()
            );
        } else {
            return new EstudianteOutputSimpleDTO(
                    this.id_student,
                    this.num_hours_week,
                    this.coments,
                    this.branch
            );
        }

    }

    public EstudianteOutputSimpleDTO estudianteToEstudianteSImpleOutputDTO() {
        return new EstudianteOutputSimpleDTO(
                this.id_student,
                this.num_hours_week,
                this.coments,
                this.branch
        );
    }
    public EstudianteOutputDTO estudianteFullDTO() {
        return new EstudianteOutputDTO(
                this.id_student,
                this.person,
                this.estudianteAsignaturas,
                this.num_hours_week,
                this.coments,
                this.branch
        );
    }
}

