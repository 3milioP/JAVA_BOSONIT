package com.example.ejercicio.block7crudvalidationJUnitMockito.controller;

import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaServiceImpl;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class GETController {

    @Autowired
    PersonaServiceImpl personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Persona>> getPersonasByNombre(@PathVariable String nombre) {
        List<Persona> personas = personaService.getPersonasByNombre(nombre);
        return ResponseEntity.ok(personas);
    }
}
