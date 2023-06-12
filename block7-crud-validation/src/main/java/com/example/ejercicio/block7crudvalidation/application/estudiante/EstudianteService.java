package com.example.ejercicio.block7crudvalidation.application.estudiante;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;

public interface EstudianteService {
    EstudianteOutputSimpleDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) throws UnprocessableEntityException;
    Iterable<EstudianteOutputSimpleDTO> getAllEstudiantes(int pageNumber, int pageSize);
    EstudianteOutputSimpleDTO getEstudianteById(String type, String id) throws EntityNotFoundException;
    ResponseEntity<String> deleteEstudianteById(String id) throws  EntityNotFoundException;
    ResponseEntity<String> updateEstudiante(String id, EstudianteInputDTO estudianteInputDTO) throws EntityNotFoundException;
}
