package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaFullOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaOutputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Asignaturas_Estudiantes")
public class Estudiante_Asignatura {
    @Id
    @GeneratedValue
    int id_asignatura;
    @ManyToMany(mappedBy = "estudianteAsignaturas")
    List<Estudiante> id_student;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;

    public Estudiante_Asignatura(Estudiante_AsignaturaInputDTO estudianteAsignaturaInputDTO) {
        this.id_asignatura = estudianteAsignaturaInputDTO.getId_asignatura();
        this.asignatura = estudianteAsignaturaInputDTO.getAsignatura();
        this.coments = estudianteAsignaturaInputDTO.getComents();
        this.initial_date = estudianteAsignaturaInputDTO.getInitial_date();
        this.finish_date = estudianteAsignaturaInputDTO.getFinish_date();
    }

    public Estudiante_AsignaturaOutputDTO estudianteAsignaturaToEstudianteAsignaturaOutputDTO() {
        return new Estudiante_AsignaturaOutputDTO(
                this.id_asignatura,
                this.asignatura,
                this.coments,
                this.initial_date,
                this.finish_date
        );
    }

    public Estudiante_AsignaturaFullOutputDTO asignaturaFullOutputDTO(){
        return new Estudiante_AsignaturaFullOutputDTO(
                this.id_asignatura,
                this.id_student,
                this.asignatura,
                this.coments,
                this.initial_date,
                this.finish_date
        );
    }
}
