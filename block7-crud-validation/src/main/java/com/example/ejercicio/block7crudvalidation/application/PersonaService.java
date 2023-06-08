package com.example.ejercicio.block7crudvalidation.application;

import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonaService {

    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws UnprocessableEntityException;
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    List<Persona> getPersonasByUsuario(String usuario);
    PersonaOutputDTO getPersonaById(int id) throws EntityNotFoundException;
    ResponseEntity<String> deletePersonaById(int id) throws  EntityNotFoundException;
    ResponseEntity<String> updatePersona(int id, PersonaInputDTO persona) throws EntityNotFoundException;
}
