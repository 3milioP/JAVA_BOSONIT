package com.ejercicio.block16springcloud.Backend.Viaje.Application;

import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeInputDTO;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeOutputDTO;


public interface ViajeService {
    ViajeOutputDTO addViaje(ViajeInputDTO viajeInputDTO);
    ViajeOutputDTO getViajeById(int id);
    ViajeOutputDTO updateViaje(int id, ViajeInputDTO viajeInputDTO);
    void deleteViaje(int id);
    void addPassengerToViaje(int idViaje, int idCliente);
    int countPassengersInViaje(int tripId);
    void updateTripStatus(int tripId, String tripStatus);
    boolean isTripAvailable(int tripId);
}
