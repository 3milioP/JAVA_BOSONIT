package com.ejercicio.backendFront.backendFront.Ticket.Application;

import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.ClienteOutputDTO;
import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.TicketOutputDTO;
import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.ViajeOutputDTO;
import com.ejercicio.backendFront.backendFront.Ticket.Domain.Ticket;
import com.ejercicio.backendFront.backendFront.Ticket.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    FeignCliente feignCliente;
    @Autowired
    FeignViaje feignViaje;

    @Autowired
    TicketRepository ticketRepository;

    public TicketOutputDTO generateTicket(int userId, int tripId) {
        // Llamada al backend para obtener los datos del cliente y el viaje
        ClienteOutputDTO cliente = feignCliente.getClienteById(userId);
        ViajeOutputDTO viaje = feignViaje.getViajeById(tripId);

        // Crear el objeto Ticket utilizando los datos obtenidos
        Ticket ticket = new Ticket();
        ticket.setPassengerId(cliente.getId());
        ticket.setPassengerName(cliente.getNombre());
        ticket.setPassengerLastName(cliente.getApellido());
        ticket.setPassengerEmail(cliente.getEmail());
        ticket.setTripOrigin(viaje.getOrigin());
        ticket.setTripDestination(viaje.getDestination());
        ticket.setDepartureDate(viaje.getDepartureDate());
        ticket.setArrivalDate(viaje.getArrivalDate());

        // Guardar el ticket en la base de datos
        return ticketRepository.save(ticket).ticketToTicketOutputDto();

    }
}
