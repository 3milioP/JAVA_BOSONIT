package com.example.ejercicio.block7crud.controller;

import com.example.ejercicio.block7crud.application.PersonaService;
import com.example.ejercicio.block7crud.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crud.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ejercicio.block7crud.domain.Persona;
import java.util.Optional;

@RestController
@RequestMapping("persona")
public class PUTController {

    @Autowired
    private PersonaRepository personaRepository;

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePersona(@PathVariable Integer id, @RequestBody Persona personaInput) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            if (personaInput.getNombre() != null && personaInput.getPoblacion() != null && personaInput.getEdad() >= 0) {
                Persona persona = personaOptional.get();
                persona.setNombre(personaInput.getNombre());
                persona.setEdad(personaInput.getEdad());
                persona.setPoblacion(personaInput.getPoblacion());
                return ResponseEntity.ok(personaRepository.save(persona));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
