package com.example.ejercicio.block7crud.domain;

import com.example.ejercicio.block7crud.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crud.controller.dto.PersonaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    int id;
    int edad;
    String nombre;
    String poblacion;

    public Persona(PersonaInputDto personaInputDto) {
        this.id = personaInputDto.getId();
        this.edad = personaInputDto.getEdad();
        this.nombre = personaInputDto.getNombre();
        this.poblacion = personaInputDto.getPoblacion();
    }

    public PersonaOutputDto personaToPersonaOutputDto() {
        return new PersonaOutputDto(
                this.id,
                this.nombre,
                this.edad,
                this.poblacion
        );
    }
}




