package com.example.ejercicio.block7crudvalidation.controller;

import com.example.ejercicio.block7crudvalidation.application.estudiante.EstudianteServiceImpl;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping("estudiante")
public class EstudianteControllers {

    @Autowired
    EstudianteServiceImpl estudianteService;

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteOutputSimpleDTO> getEstudianteById(
            @PathVariable int id, @RequestParam (name="OutputType", defaultValue="simple", required = false) String OutputType
    ) {
        return ResponseEntity.ok().body(estudianteService.getEstudianteById(OutputType, id));
    }

    @GetMapping
    public Iterable<EstudianteOutputSimpleDTO> getAllEstudiantes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return estudianteService.getAllEstudiantes(pageNumber, pageSize);
    }

    @PostMapping
    public ResponseEntity<EstudianteOutputSimpleDTO> addEstudiante(@RequestBody EstudianteInputDTO estudianteInputDTO) {
        try {
            return ResponseEntity.ok().body(estudianteService.addEstudiante(estudianteInputDTO));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEstudianteById(@RequestParam int id) {
        return estudianteService.deleteEstudianteById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateEsudiante(@RequestParam int id, @RequestBody EstudianteInputDTO estudiante) {
        return estudianteService.updateEstudiante(id, estudiante);
    }
}
