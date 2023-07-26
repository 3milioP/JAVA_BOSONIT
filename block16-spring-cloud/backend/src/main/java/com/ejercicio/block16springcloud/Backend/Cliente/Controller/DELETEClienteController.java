package com.ejercicio.block16springcloud.Backend.Cliente.Controller;

import com.ejercicio.block16springcloud.Backend.Cliente.Application.ClienteServiceImpl;
import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import com.ejercicio.block16springcloud.Backend.Exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class DELETEClienteController {

    @Autowired
    ClienteServiceImpl clienteServiceImpl;

    @DeleteMapping("/deleteCliente")
    public ResponseEntity<String> deleteCliente(@RequestParam int id) {
        try {
            clienteServiceImpl.deleteCliente(id);
            return ResponseEntity.ok().body("El cliente con id " + id + " ha sido eliminado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno en el servidor.");
        }
    }
}

