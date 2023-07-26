package com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ViajeInputDTO {
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;;
    String status;
}
