package com.example.ejercicio.block7crudvalidation.controller;

import com.example.ejercicio.block7crudvalidation.application.client.FeignClient;
import com.example.ejercicio.block7crudvalidation.application.persona.PersonaServiceImpl;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaDetailOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonaControllers {
    @Autowired
    PersonaServiceImpl personaService;
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    FeignClient feignClient;

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

    @GetMapping("/profesor/{id}")
    public ProfesorOutputDTO getProfesor(@PathVariable int id) {
        return feignClient.getProfesorById(id);
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
