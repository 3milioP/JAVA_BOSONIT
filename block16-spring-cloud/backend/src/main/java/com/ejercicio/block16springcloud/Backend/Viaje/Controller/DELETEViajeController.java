package com.ejercicio.block16springcloud.Backend.Viaje.Controller;

import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import com.ejercicio.block16springcloud.Backend.Viaje.Application.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class DELETEViajeController {

    @Autowired
    ViajeService viajeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViaje(@PathVariable int id) {
        try {
            viajeService.deleteViaje(id);
            // Devuelve una respuesta exitosa con un mensaje de éxito
            return ResponseEntity.ok("El viaje con ID " + id + " ha sido eliminado.");
        } catch (EntityNotFoundException e) {
            // Si el viaje no es encontrado, devuelve una respuesta de error 404 (Not Found) con un mensaje descriptivo
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Si ocurre una excepción inesperada, devuelve una respuesta de error 500 (Internal Server Error) con un mensaje genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno en el servidor.");
        }
    }
}

