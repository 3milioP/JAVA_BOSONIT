package com.example.ejercicio.block6pathvariableheaders.controllers;

import com.example.ejercicio.block6pathvariableheaders.model.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GetGreeting {

    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!";
    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(value= "name", defaultValue = "World") String name) {
        Greeting response = new Greeting(counter.incrementAndGet(), String.format(template, name));

        return ResponseEntity.ok(response);
    }
}
