package com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDTO {
    int id;
    String nombre;
    String apellido;
    int edad;
    String email;
    int telefono;
}
