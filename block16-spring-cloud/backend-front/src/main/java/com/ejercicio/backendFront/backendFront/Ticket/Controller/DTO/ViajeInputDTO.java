package com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ViajeInputDTO {
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;;
    String status;
}
