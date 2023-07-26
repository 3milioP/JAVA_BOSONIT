package com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO;


import com.ejercicio.backendFront.backendFront.Ticket.Domain.Cliente;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ViajeOutputDTO {
    int id;
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;
    List<Cliente> passengers;
    String status;
}
