package com.example.ejercicio.block7crudvalidationJUnitMockito.controller;

import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
