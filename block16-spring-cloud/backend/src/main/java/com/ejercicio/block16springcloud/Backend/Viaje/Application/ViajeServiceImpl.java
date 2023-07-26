package com.ejercicio.block16springcloud.Backend.Viaje.Application;

import com.ejercicio.block16springcloud.Backend.Cliente.Domain.Cliente;
import com.ejercicio.block16springcloud.Backend.Cliente.Repository.ClienteRepository;
import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import com.ejercicio.block16springcloud.Backend.Exceptions.UnprocessableEntityException;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeInputDTO;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeOutputDTO;
import com.ejercicio.block16springcloud.Backend.Viaje.Domain.Viaje;
import com.ejercicio.block16springcloud.Backend.Viaje.Repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    ViajeRepository viajeRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ViajeOutputDTO addViaje(ViajeInputDTO viajeInputDTO) {
        // Verifica que los datos de entrada sean válidos antes de guardar el viaje
        if (viajeInputDTO.getOrigin() != null && viajeInputDTO.getDestination() != null &&
                viajeInputDTO.getDepartureDate() != null && viajeInputDTO.getArrivalDate() != null &&
                viajeInputDTO.getStatus() != null) {
            Viaje viaje = viajeRepository.save(new Viaje(viajeInputDTO));
            return viaje.ViajeToViajeOutputDTO();
        } else {
            throw new UnprocessableEntityException("Datos de entrada no válidos para agregar un viaje.");
        }
    }

    @Override
    public ViajeOutputDTO getViajeById(int id) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
        return viaje.ViajeToViajeOutputDTO();
    }

    @Override
    public ViajeOutputDTO updateViaje(int id, ViajeInputDTO viajeInputDTO) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));

        // Verifica que los datos de entrada sean válidos antes de actualizar el viaje
        if (viajeInputDTO.getOrigin() != null && viajeInputDTO.getDestination() != null &&
                viajeInputDTO.getDepartureDate() != null && viajeInputDTO.getArrivalDate() != null &&
                viajeInputDTO.getStatus() != null) {
            viaje.setOrigin(viajeInputDTO.getOrigin());
            viaje.setDestination(viajeInputDTO.getDestination());
            viaje.setDepartureDate(viajeInputDTO.getDepartureDate());
            viaje.setArrivalDate(viajeInputDTO.getArrivalDate());
            viaje.setStatus(viajeInputDTO.getStatus());
            viajeRepository.save(viaje);
            return viaje.ViajeToViajeOutputDTO();
        } else {
            throw new UnprocessableEntityException("Datos de entrada no válidos para actualizar el viaje.");
        }
    }

    @Override
    public void deleteViaje(int id) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
        viajeRepository.delete(viaje);
    }

    @Override
    public void addPassengerToViaje(int tripId, int passengerId) {
        Viaje viaje = viajeRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + tripId));
        Cliente cliente = clienteRepository.findById(passengerId).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + passengerId));
        List<Cliente> pasajeros = viaje.getPassengers();

        if (pasajeros.size() < 40) {
            pasajeros.add(cliente);
            viajeRepository.save(viaje);
        } else {
            throw new UnprocessableEntityException("No se pueden agregar más pasajeros, el viaje ya está completo.");
        }
    }
    @Override
    public int countPassengersInViaje(int tripId) {
        Viaje viaje = viajeRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + tripId));
        return viaje.getPassengers().size();
    }

    @Override
    public void updateTripStatus(int tripId, String tripStatus) {
        Viaje viaje = viajeRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + tripId));
        viaje.setStatus(tripStatus);
        viajeRepository.save(viaje);
    }

    @Override
    public boolean isTripAvailable(int tripId) {
        Viaje viaje = viajeRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + tripId));
        String tripStatus = viaje.getStatus();
        return "disponible".equalsIgnoreCase(tripStatus); // Se verifica si el estado del viaje es "disponible" ignorando mayúsculas y minúsculas
    }

}

