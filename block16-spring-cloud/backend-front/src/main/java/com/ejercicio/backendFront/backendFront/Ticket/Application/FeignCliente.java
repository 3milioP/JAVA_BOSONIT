package com.ejercicio.backendFront.backendFront.Ticket.Application;

import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.ClienteOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignCliente", url = "${feignCliente.url}")
public interface FeignCliente {
    @GetMapping("/cliente/{id}")
    public ClienteOutputDTO getClienteById(@PathVariable Integer id);
}