package com.example.ejercicio.block6personcontrollers.controladores;

import com.example.ejercicio.block6personcontrollers.modelos.Persona;
import com.example.ejercicio.block6personcontrollers.servicios.PersonaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ejercicio.block6personcontrollers.modelos.Ciudad;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final PersonaService personaService;
    private final List<Ciudad> ciudades;

    public Controlador2(PersonaService personaService, List<Ciudad> ciudades) {
        this.personaService = personaService;
        this.ciudades = ciudades;
    }

    @GetMapping("/getPersona")
    public Persona getPersona() {
        Persona persona = personaService.getPersona();
        persona.multiplicadoredad();
        return persona;
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades() {
        return ciudades;
    }
}
