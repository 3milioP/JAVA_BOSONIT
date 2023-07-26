package com.ejercicio.block16springcloud.Backend.Viaje.Domain;

import com.ejercicio.block16springcloud.Backend.Cliente.Domain.Cliente;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeOutputDTO;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@Table(name = "viaje")
@NoArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue
    int id;
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;
    @ManyToMany
    List<Cliente> passengers;
    String status;

    public Viaje(ViajeInputDTO viajeInputDTO) {
        this.origin = viajeInputDTO.getOrigin();
        this.destination = viajeInputDTO.getDestination();
        this.departureDate = viajeInputDTO.getDepartureDate();
        this.arrivalDate = viajeInputDTO.getArrivalDate();
        this.status = viajeInputDTO.getStatus();
    }

    public ViajeOutputDTO ViajeToViajeOutputDTO() {
        return new ViajeOutputDTO(this.id, this.origin, this.destination, this.departureDate, this.arrivalDate, this.passengers, this.status);
    }
}
