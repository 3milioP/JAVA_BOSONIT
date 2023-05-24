package com.example.ejercicio.block6personcontrollers.servicios;

import com.example.ejercicio.block6personcontrollers.modelos.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    private Persona persona;

    public Persona addPersona(String nombre, String poblacion, int edad) {
        this.persona = new Persona(nombre, poblacion, edad);
        return this.persona;
    }

    public Persona getPersona() {
        return this.persona;
    }
}
