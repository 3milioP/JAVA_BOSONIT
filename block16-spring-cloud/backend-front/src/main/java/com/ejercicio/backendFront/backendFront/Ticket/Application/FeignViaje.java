package com.ejercicio.backendFront.backendFront.Ticket.Application;

import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.ViajeOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="feignViaje", url="${feignViaje.url}")
public interface FeignViaje {
    @GetMapping("/trip/{id}")
    public ViajeOutputDTO getViajeById(@PathVariable Integer id);
}