package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputFullDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String id_student;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona id_persona;
    @OneToMany
    List<Estudiante_Asignatura> estudianteAsignatura;
    @NotNull
    int num_hours_week;
    String coments;
    @NotNull
    String branch;

    public Estudiante(EstudianteInputDTO estudianteInputDTO) {
        this.num_hours_week = estudianteInputDTO.getNum_hours_week();
        this.coments = estudianteInputDTO.getComents();
        this.branch = estudianteInputDTO.getBranch();
    }

    public EstudianteOutputSimpleDTO estudianteToEstudianteOutputDTO(String type) {
        if (type.equals("full")) {
            return new EstudianteOutputFullDTO(
                    this.id_student,
                    this.id_persona.getId_persona(),
                    this.num_hours_week,
                    this.coments,
                    this.branch,
                    this.id_persona.getUsuario(),
                    this.id_persona.getName(),
                    this.id_persona.getSurname(),
                    this.id_persona.getCompany_email(),
                    this.id_persona.getPersonal_email(),
                    this.id_persona.getCity(),
                    this.id_persona.getActive(),
                    this.id_persona.getCreated_date(),
                    this.id_persona.getImagen_url(),
                    this.id_persona.getTermination_date()
            );
        } else {
            return new EstudianteOutputSimpleDTO(
                    this.id_student,
                    this.id_persona.getId_persona(),
                    this.num_hours_week,
                    this.coments,
                    this.branch
            );
        }

    }

    public EstudianteOutputSimpleDTO estudianteToEstudianteSImpleOutputDTO() {
        return new EstudianteOutputSimpleDTO(
                this.id_student,
                this.id_persona.getId_persona(),
                this.num_hours_week,
                this.coments,
                this.branch
        );
    }
}

