package com.ejercicio.backendFront.backendFront.Ticket.Controller;


import com.ejercicio.backendFront.backendFront.Ticket.Application.TicketService;
import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.TicketOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/generateTicket/{userId}/{tripId}")
    public ResponseEntity<TicketOutputDTO> generateTicket(@PathVariable int userId, @PathVariable int tripId) {
        TicketOutputDTO ticket = ticketService.generateTicket(userId, tripId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}