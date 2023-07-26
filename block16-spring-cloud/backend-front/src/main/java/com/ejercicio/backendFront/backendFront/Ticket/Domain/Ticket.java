package com.ejercicio.backendFront.backendFront.Ticket.Domain;

import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.TicketInputDTO;
import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.TicketOutputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "tickets")
public class Ticket {
    @Id
    ObjectId id;
    int passengerId;
    String passengerName;
    String passengerLastName;
    String passengerEmail;
    String tripOrigin;
    String tripDestination;
    Date departureDate;
    Date arrivalDate;

    public TicketOutputDTO ticketToTicketOutputDto() {
        TicketOutputDTO ticketOutputDto = new TicketOutputDTO();

        ticketOutputDto.setId(getId());
        ticketOutputDto.setPassengerId(getPassengerId());
        ticketOutputDto.setPassengerName(getPassengerName());
        ticketOutputDto.setPassengerLastname(getPassengerLastName());
        ticketOutputDto.setPassengerEmail(getPassengerEmail());
        ticketOutputDto.setTripOrigin(getTripOrigin());
        ticketOutputDto.setTripDestination(getTripDestination());
        ticketOutputDto.setDepartureDate(getDepartureDate());
        ticketOutputDto.setArrivalDate(getArrivalDate());

        return ticketOutputDto;
    }

    public Ticket(TicketInputDTO ticketInputDto) {
        setPassengerId(ticketInputDto.getPassengerId());
        setPassengerName(ticketInputDto.getPassengerName());
        setPassengerLastName(ticketInputDto.getPassengerLastname());
        setPassengerEmail(ticketInputDto.getPassengerEmail());
        setTripOrigin(ticketInputDto.getTripOrigin());
        setTripDestination(ticketInputDto.getTripDestination());
        setDepartureDate(ticketInputDto.getDepartureDate());
        setArrivalDate(ticketInputDto.getArrivalDate());
    }
}
