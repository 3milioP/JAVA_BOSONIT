package com.example.ejercicio.block7crudvalidation.application;

import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception {

        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow().personaToPersonaOutputDTO();
    }

    @Override
    public Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent().stream()
                .map(Persona::personaToPersonaOutputDTO).toList();
    }

    @Override
    public List<Persona> getPersonasByUsuario(String usuario) {
        return personaRepository.findByUsuario(usuario);
    }
}
