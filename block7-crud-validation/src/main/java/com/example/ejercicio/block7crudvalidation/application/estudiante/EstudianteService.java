package com.example.ejercicio.block7crudvalidation.application.estudiante;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;

public interface EstudianteService {
    EstudianteOutputSimpleDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) throws UnprocessableEntityException;
    Iterable<EstudianteOutputSimpleDTO> getAllEstudiantes(int pageNumber, int pageSize);
    EstudianteOutputSimpleDTO getEstudianteById(String type, int id) throws EntityNotFoundException;
    ResponseEntity<String> deleteEstudianteById(int id) throws  EntityNotFoundException;
    ResponseEntity<String> updateEstudiante(int id, EstudianteInputDTO estudianteInputDTO) throws EntityNotFoundException;

    EstudianteOutputDTO getEstudianteByid2(int idStudent);
}
