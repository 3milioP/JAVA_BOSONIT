package com.ejercicio.block16springcloud.Backend.Cliente.Controller;

import com.ejercicio.block16springcloud.Backend.Cliente.Application.ClienteService;
import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteInputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class POSTClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/addCliente")
    public ResponseEntity<ClienteOutputDTO> addCliente(@RequestBody ClienteInputDTO clienteInputDTO) {
        ClienteOutputDTO clienteOutputDTO = clienteService.addCliente(clienteInputDTO);
        // Devuelve una respuesta exitosa con el objeto ClienteOutputDTO creado
        return ResponseEntity.ok(clienteOutputDTO);
    }
}

