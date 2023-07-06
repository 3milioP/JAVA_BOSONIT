package com.example.ejercicio.block7crudvalidationJUnitMockito.controller;

import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaService;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("persona")
public class POSTController {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }
}
