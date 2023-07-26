package com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO;

import com.ejercicio.block16springcloud.Backend.Cliente.Domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class ViajeOutputDTO {
    int id;
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;
    List<Cliente> passengers;
    String status;
}
