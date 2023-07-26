package com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInputDTO {
    String nombre;
    String apellido;
    int edad;
    String email;
    int telefono;
}
