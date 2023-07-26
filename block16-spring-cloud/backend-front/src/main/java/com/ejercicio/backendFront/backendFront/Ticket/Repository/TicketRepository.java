package com.ejercicio.backendFront.backendFront.Ticket.Repository;

import com.ejercicio.backendFront.backendFront.Ticket.Domain.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, Integer> {
}
