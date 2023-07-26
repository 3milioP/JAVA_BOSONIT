package com.ejercicio.block16springcloud.Backend.Viaje.Controller;

import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import com.ejercicio.block16springcloud.Backend.Exceptions.UnprocessableEntityException;
import com.ejercicio.block16springcloud.Backend.Viaje.Application.ViajeService;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeInputDTO;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
public class PUTViajeController {

    @Autowired
    ViajeService viajeService;

    @PutMapping("/{id}")
    public ResponseEntity<ViajeOutputDTO> updateViaje(@PathVariable int id, @RequestBody ViajeInputDTO viajeInputDTO) {
        try {
            ViajeOutputDTO viajeOutputDTO = viajeService.updateViaje(id, viajeInputDTO);
            // Devuelve una respuesta exitosa con el objeto ViajeOutputDTO actualizado
            return ResponseEntity.ok(viajeOutputDTO);
        } catch (EntityNotFoundException e) {
            // Si el viaje no es encontrado, devuelve una respuesta de error 404 (Not Found) con un mensaje descriptivo
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            // Si los datos de entrada no son válidos para actualizar el viaje, devuelve una respuesta de error 422 (Unprocessable Entity) con el mensaje de error
            return ResponseEntity.unprocessableEntity().build();
        } catch (Exception e) {
            // Si ocurre una excepción inesperada, devuelve una respuesta de error 500 (Internal Server Error) con un mensaje genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/addPassenger/{tripId}/{passengerId}")
    public ResponseEntity<String> addPassengerToViaje(@PathVariable int tripId, @PathVariable int passengerId) {
        try {
            viajeService.addPassengerToViaje(tripId, passengerId);
            return ResponseEntity.ok("Pasajero añadido exitosamente al viaje con ID " + tripId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno en el servidor.");
        }
    }
}
