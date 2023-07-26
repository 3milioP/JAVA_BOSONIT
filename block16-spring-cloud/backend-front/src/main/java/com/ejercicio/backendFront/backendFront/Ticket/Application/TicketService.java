package com.ejercicio.backendFront.backendFront.Ticket.Application;

import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.TicketOutputDTO;

public interface TicketService {
    TicketOutputDTO generateTicket(int userId, int tripId);
}
