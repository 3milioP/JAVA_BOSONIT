package com.example.ejercicio.block7crud.controller;

import com.example.ejercicio.block7crud.application.PersonaService;
import com.example.ejercicio.block7crud.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crud.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persona")
public class DELETEController {

    @Autowired
    PersonaService personaService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonaById(@RequestParam int id) {
        try {
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body("persona with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
