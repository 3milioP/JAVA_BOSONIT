package com.ejercicio.block16springcloud.Backend.Cliente.Controller;

import com.ejercicio.block16springcloud.Backend.Cliente.Application.ClienteService;
import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteInputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteOutputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Repository.ClienteRepository;
import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class PUTClienteController {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutputDTO> updateCliente(@PathVariable int id, @RequestBody ClienteInputDTO clienteInputDTO) {
        try {
            // Llama al método de servicio para actualizar el cliente
            ClienteOutputDTO clienteActualizado = clienteService.updateCliente(id, clienteInputDTO);

            // Si el método updateCliente no lanza una excepción, significa que se actualizó correctamente.
            // Entonces, devolvemos una respuesta exitosa (código 200) con el objeto ClienteOutputDTO actualizado.
            return ResponseEntity.ok(clienteActualizado);
        } catch (IllegalArgumentException e) {
            // Si los datos de entrada no son válidos, devolvemos una respuesta de error 422 (Unprocessable Entity) con un mensaje de error.
            return ResponseEntity.unprocessableEntity().build();
        } catch (EntityNotFoundException e) {
            // Si el cliente no se encuentra, devolvemos una respuesta de error 404 (Not Found) con un mensaje de error.
            return ResponseEntity.notFound().build();
        }
    }
}

