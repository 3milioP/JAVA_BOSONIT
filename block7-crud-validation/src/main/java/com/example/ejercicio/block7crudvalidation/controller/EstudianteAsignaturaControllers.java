package com.example.ejercicio.block7crudvalidation.controller;

import com.example.ejercicio.block7crudvalidation.application.estudiante_asignatura.Estudiante_AsignaturaService;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante_Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("asignatura")
public class EstudianteAsignaturaControllers {
    @Autowired
    Estudiante_AsignaturaService estudianteAsignaturaService;

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante_AsignaturaOutputDTO> getEstudianteAsignaturaById(@PathVariable int id) {
        return ResponseEntity.ok().body(estudianteAsignaturaService.getEstudianteAsignaturaById(id));
    }
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<Iterable<Estudiante_Asignatura>> getAsignaturasEstudiante(@PathVariable int id) {
        return ResponseEntity.ok(estudianteAsignaturaService.getAsignaturasEstudiante(id));
    }

    @GetMapping
    public Iterable<Estudiante_AsignaturaOutputDTO> getAllEstudiantes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return estudianteAsignaturaService.getAllEstudiantesAsignatura(pageNumber, pageSize);
    }

    @PostMapping
    public ResponseEntity<Estudiante_AsignaturaOutputDTO> addEstudianteAsignatura(@RequestBody Estudiante_AsignaturaInputDTO asignatura) {
        URI location = URI.create("/asignatura");
        return ResponseEntity.created(location).body(estudianteAsignaturaService.addEstudianteAsignatura(asignatura));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEstudianteAsignaturaById(@RequestParam int id) {
        return estudianteAsignaturaService.deleteEstudianteAsignaturaById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateEstudianteAsignatura(@RequestParam int id, @RequestBody Estudiante_AsignaturaInputDTO asignatura) {
        return estudianteAsignaturaService.updateEstudianteAsignatura(id, asignatura);
    }
}
