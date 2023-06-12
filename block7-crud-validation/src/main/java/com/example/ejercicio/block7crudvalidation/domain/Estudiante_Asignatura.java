package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaOutputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Asignaturas_Estudiantes")
public class Estudiante_Asignatura {
    @Id
    @GeneratedValue
    String id_asignatura;
    @ManyToOne
    @JoinColumn(name = "id_student")
    Estudiante id_student;
    String signature;
    String coments;
    @NotNull
    Date initial_date;
    Date finish_date;

    public Estudiante_Asignatura(Estudiante_AsignaturaInputDTO estudianteAsignaturaInputDTO) {
        this.id_student = estudianteAsignaturaInputDTO.getId_student();
        this.signature = estudianteAsignaturaInputDTO.getSignature();
        this.coments = estudianteAsignaturaInputDTO.getComents();
        this.initial_date = estudianteAsignaturaInputDTO.getInitial_date();
        this.finish_date = estudianteAsignaturaInputDTO.getFinish_date();
    }

    public Estudiante_AsignaturaOutputDTO estudianteAsignaturaToEstudianteAsignaturaOutputDTO() {
        return new Estudiante_AsignaturaOutputDTO(
                this.id_asignatura,
                this.id_student,
                this.signature,
                this.coments,
                this.initial_date,
                this.finish_date
        );
    }
}
