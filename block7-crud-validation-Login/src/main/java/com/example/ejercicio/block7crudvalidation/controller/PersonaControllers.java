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
import java.util.Optional;

@RestController
@RequestMapping("")
public class PersonaControllers {
    @Autowired
    PersonaServiceImpl personaService;
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    FeignClient feignClient;

    @GetMapping("persona/{id}")
    public ResponseEntity<PersonaOutputDTO> getPersonaById(@PathVariable int id) {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getall")
    public Iterable<PersonaOutputDTO> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @GetMapping("persona/nombre/{nombre}")
    public ResponseEntity<Optional<Persona>> getPersonasByUsuario(@PathVariable String nombre) {
        Optional<Persona> personas = personaService.getPersonasByUsuario(nombre);
        return ResponseEntity.ok(personas);
    }

    @GetMapping("persona/profesor/{id}")
    public ProfesorOutputDTO getProfesor(@PathVariable int id) {
        return feignClient.getProfesorById(id);
    }

    @PostMapping("/persona")
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO persona) {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutputDTO> addPersonaCors(@RequestBody PersonaInputDTO persona) {
        URI location = URI.create("/addperson");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @DeleteMapping("/persona")
    public ResponseEntity<String> deletePersonaById(@RequestParam int id) {
        return personaService.deletePersonaById(id);
    }

    @PutMapping("/persona")
    public ResponseEntity<String> updatePersona(@RequestParam int id, @RequestBody PersonaInputDTO persona) {
        return personaService.updatePersona(id, persona);
    }
}
