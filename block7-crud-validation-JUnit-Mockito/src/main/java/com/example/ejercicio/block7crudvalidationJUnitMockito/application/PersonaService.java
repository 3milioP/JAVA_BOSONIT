package com.example.ejercicio.block7crudvalidationJUnitMockito.application;


import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaOutputDto getPersonaById(int id);
    void deletePersonaById(int id);
    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto updatePersona(int id, PersonaInputDto persona);
    List<Persona> getPersonasByNombre(String nombre);


}
