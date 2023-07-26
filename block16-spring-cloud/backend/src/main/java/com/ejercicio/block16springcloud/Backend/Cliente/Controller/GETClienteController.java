package com.ejercicio.block16springcloud.Backend.Cliente.Controller;

import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteOutputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Application.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class GETClienteController {

    @Autowired
    ClienteServiceImpl clienteServiceImpl;
    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDTO> getClienteById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(clienteServiceImpl.getClienteById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("getAllClientes")
    public List<ClienteOutputDTO> getAllClientes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ) {
        return clienteServiceImpl.getAllClientes(pageNumber, pageSize);
    }
}
