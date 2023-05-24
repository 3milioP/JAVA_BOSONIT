package com.example.ejercicio.block6personcontrollers.controladores;

import com.example.ejercicio.block6personcontrollers.modelos.Persona;
import com.example.ejercicio.block6personcontrollers.servicios.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ejercicio.block6personcontrollers.modelos.Ciudad;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final PersonaService personaService;
    private final List<Ciudad> ciudades;

    public Controlador1(PersonaService personaService, List<Ciudad> ciudades) {
        this.personaService = personaService;
        this.ciudades = ciudades;
    }

    @GetMapping("/addPersona")
    public Persona agregarPersona(@RequestHeader("nombre") String nombre,
                                  @RequestHeader("poblacion") String poblacion,
                                  @RequestHeader("edad") int edad) {
        return personaService.addPersona(nombre, poblacion, edad);
    }

    @PostMapping("/addCiudad")
    public ResponseEntity<String> addCiudad(@RequestBody Ciudad ciudad) {
        ciudades.add(ciudad);
        return ResponseEntity.ok("Ciudad agregada correctamente");
    }

}