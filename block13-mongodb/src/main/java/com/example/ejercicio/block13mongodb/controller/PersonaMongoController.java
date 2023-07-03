package com.example.ejercicio.block13mongodb.controller;

import com.example.ejercicio.block13mongodb.application.PersonaMongoService;
import com.example.ejercicio.block13mongodb.domain.PersonaMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaMongoController {

    @Autowired
    PersonaMongoService personService;

    @PostMapping
    public ResponseEntity<PersonaMongo> createPersonaMongo(@RequestBody PersonaMongo personaMongo) {
        return ResponseEntity.ok().body(personService.guardar(personaMongo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaMongo> getPersonById(@PathVariable String id) {
        return ResponseEntity.ok().body(personService.obtenerPorId(id));
    }

    @GetMapping
    public List<PersonaMongo> getAllPersons(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return personService.obtenerTodos(pageable);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable String id) {
        personService.eliminar(id);
    }
}
