package com.example.ejercicio.block7crudvalidation.application.client;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name="app8081", url="http://localhost:8081")
public interface FeignClient {

    @GetMapping("/profesor/{id}")
    ProfesorOutputDTO getProfesorById(@PathVariable int id);
}
