package com.example.ejercicio.block7crud.application;


import com.example.ejercicio.block7crud.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crud.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crud.domain.Persona;
import java.util.List;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaOutputDto getPersonaById(int id);
    void deletePersonaById(int id);
    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto updatePersona(int id, PersonaInputDto persona);
    List<Persona> getPersonasByNombre(String nombre);


}
