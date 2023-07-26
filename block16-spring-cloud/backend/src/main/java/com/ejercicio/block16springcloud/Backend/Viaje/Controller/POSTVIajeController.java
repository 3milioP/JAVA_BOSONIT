package com.ejercicio.block16springcloud.Backend.Viaje.Controller;

import com.ejercicio.block16springcloud.Backend.Viaje.Application.ViajeService;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeInputDTO;
import com.ejercicio.block16springcloud.Backend.Viaje.Controller.DTO.ViajeOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class POSTVIajeController {

    @Autowired
    ViajeService viajeService;

    @PostMapping("/addViaje")
    public ResponseEntity<ViajeOutputDTO> addViaje(@RequestBody ViajeInputDTO viajeInputDTO) {
        ViajeOutputDTO viajeOutputDTO = viajeService.addViaje(viajeInputDTO);
        // Devuelve una respuesta exitosa con el objeto ViajeOutputDTO creado
        return ResponseEntity.ok(viajeOutputDTO);
    }
}
