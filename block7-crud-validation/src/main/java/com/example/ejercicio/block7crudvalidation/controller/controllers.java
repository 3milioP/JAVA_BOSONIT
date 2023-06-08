package com.example.ejercicio.block7crudvalidation.controller;

import com.example.ejercicio.block7crudvalidation.application.PersonaServiceImpl;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("persona")
public class controllers {
    @Autowired
    PersonaServiceImpl personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDTO> getPersonaById(@PathVariable int id) {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
    }

    @GetMapping
    public Iterable<PersonaOutputDTO> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Persona>> getPersonasByUsuario(@PathVariable String nombre) {
        List<Persona> personas = personaService.getPersonasByUsuario(nombre);
        return ResponseEntity.ok(personas);
    }

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO persona) {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePersonaById(@RequestParam int id) {
        return personaService.deletePersonaById(id);
    }

    @PutMapping
    public ResponseEntity<String> updatePersona(@RequestParam int id, @RequestBody PersonaInputDTO persona) {
        return personaService.updatePersona(id, persona);
    }
}
