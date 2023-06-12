package com.example.ejercicio.block7crudvalidation.application.profesor;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO professor) throws UnprocessableEntityException;
    Iterable<ProfesorOutputDTO> getAllProfesores(int pageNumber, int pageSize);
    ProfesorOutputDTO getProfesorById(String id) throws EntityNotFoundException;
    ResponseEntity<String> deleteProfesorById(String id) throws  EntityNotFoundException;
    ResponseEntity<String> updateProfesor(String id, ProfesorInputDTO professor) throws EntityNotFoundException;
}