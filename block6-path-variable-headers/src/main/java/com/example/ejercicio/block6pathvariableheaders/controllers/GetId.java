package com.example.ejercicio.block6pathvariableheaders.controllers;

import com.example.ejercicio.block6pathvariableheaders.model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class GetId {

    @GetMapping("user/{id}")
    public long greeting(@PathVariable(value="id") long id) {
        return id;
    }
}