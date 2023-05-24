package com.example.ejercicio.block6personcontrollers.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    private String nombre;
    private String poblacion;
    private int edad;

    public void multiplicadoredad() {
        this.edad *= 2;
    }

}
