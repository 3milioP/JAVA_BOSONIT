package com.example.ejercicio.block7crudvalidation.application.profesor;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) throws UnprocessableEntityException;
    Iterable<ProfesorOutputDTO> getAllProfesores(int pageNumber, int pageSize);
    ProfesorOutputDTO getProfesorById(int id) throws EntityNotFoundException;
    ResponseEntity<String> deleteProfesorById(int id) throws  EntityNotFoundException;
    ResponseEntity<String> updateProfesor(int id, ProfesorInputDTO professor) throws EntityNotFoundException;
    void addEstudianteToProfesor(int student_id, int profesor_id);
}