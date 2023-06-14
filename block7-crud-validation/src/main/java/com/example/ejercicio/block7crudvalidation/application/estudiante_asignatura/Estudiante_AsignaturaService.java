package com.example.ejercicio.block7crudvalidation.application.estudiante_asignatura;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante_Asignatura;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;

public interface Estudiante_AsignaturaService {
        Estudiante_AsignaturaOutputDTO addEstudianteAsignatura(Estudiante_AsignaturaInputDTO signature) throws UnprocessableEntityException;
        Iterable<Estudiante_AsignaturaOutputDTO> getAllEstudiantesAsignatura(int pageNumber, int pageSize);
        Estudiante_AsignaturaOutputDTO getEstudianteAsignaturaById(int id) throws EntityNotFoundException;
        ResponseEntity<String> deleteEstudianteAsignaturaById(int id) throws  EntityNotFoundException;
        ResponseEntity<String> updateEstudianteAsignatura(int id, Estudiante_AsignaturaInputDTO professor) throws EntityNotFoundException;

        Iterable<Estudiante_Asignatura> getAsignaturasEstudiante(int id);
}
