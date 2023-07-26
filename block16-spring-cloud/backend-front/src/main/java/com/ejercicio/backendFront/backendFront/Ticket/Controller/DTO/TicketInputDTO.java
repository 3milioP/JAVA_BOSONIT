package com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketInputDTO {
    Integer passengerId;
    String passengerName;
    String passengerLastname;
    String passengerEmail;
    String tripOrigin;
    String tripDestination;
    Date departureDate;
    Date arrivalDate;
}