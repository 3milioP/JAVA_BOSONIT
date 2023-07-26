package com.ejercicio.block16springcloud.Backend.Viaje.Controller;

import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import com.ejercicio.block16springcloud.Backend.Viaje.Application.ViajeService;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
class GETViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping("/{id}")
    public ResponseEntity<ViajeOutputDTO> getViajeById(@PathVariable int id) {
        try {
            // Llama al servicio para obtener el viaje por su ID
            ViajeOutputDTO viajeOutputDTO = viajeService.getViajeById(id);
            // Devuelve una respuesta exitosa con el viaje encontrado
            return ResponseEntity.ok(viajeOutputDTO);
        } catch (EntityNotFoundException e) {
            // Si el viaje no es encontrado, devuelve una respuesta de error 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/count/{tripId}")
    public ResponseEntity<String> countPassengersInViaje(@PathVariable int tripId) {
        try {
            int passengerCount = viajeService.countPassengersInViaje(tripId);
            return ResponseEntity.ok("El viaje con id " +tripId + " tiene " +passengerCount+ " pasajeros.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/verify/{tripId}")
    public ResponseEntity<String> verifyTripAvailability(@PathVariable int tripId) {
        try {
            boolean isAvailable = viajeService.isTripAvailable(tripId);
            if (isAvailable) {
                return ResponseEntity.ok("El viaje con ID " + tripId + " está disponible.");
            } else {
                return ResponseEntity.ok("El viaje con ID " + tripId + " no está disponible.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno en el servidor.");
        }
    }
}

