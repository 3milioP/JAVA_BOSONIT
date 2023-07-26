package com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteInputDTO {
    String nombre;
    String apellido;
    int edad;
    String email;
    int telefono;
}
