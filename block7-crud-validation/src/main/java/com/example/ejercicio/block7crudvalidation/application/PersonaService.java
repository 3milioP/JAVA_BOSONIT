package com.example.ejercicio.block7crudvalidation.application;

import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import java.util.List;

public interface PersonaService {

    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception;
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    List<Persona> getPersonasByUsuario(String usuario);
    PersonaOutputDTO getPersonaById(int id);
}
