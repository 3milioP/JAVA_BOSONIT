package com.example.ejercicio.block7crudvalidation.controller;

import com.example.ejercicio.block7crudvalidation.application.profesor.ProfesorServiceImpl;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("profesor")
public class ProfesorControllers {

    @Autowired
    ProfesorServiceImpl profesorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDTO> getProfesorById(@PathVariable String id) {
        return ResponseEntity.ok().body(profesorService.getProfesorById(id));
    }

    @GetMapping
    public Iterable<ProfesorOutputDTO> getAllEstudiantes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return profesorService.getAllProfesores(pageNumber, pageSize);
    }

    @PostMapping
    public ResponseEntity<ProfesorOutputDTO> addProfesor(@RequestBody ProfesorInputDTO profesor) {
        URI location = URI.create("/profesor");
        return ResponseEntity.created(location).body(profesorService.addProfesor(profesor));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProfesorById(@RequestParam String id) {
        return profesorService.deleteProfesorById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateProfesor(@RequestParam String id, @RequestBody ProfesorInputDTO profesor) {
        return profesorService.updateProfesor(id, profesor);
    }
}