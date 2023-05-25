package com.example.ejercicio.block6pathvariableheaders.controllers;

import com.example.ejercicio.block6pathvariableheaders.model.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostGreeting {

    @PostMapping("postGreeting")
    public ResponseEntity<Greeting> greeting(@RequestBody Greeting request) {
        Greeting response = new Greeting(request.getId(), request.getContent());
        return ResponseEntity.ok(response);
    }
}
