package com.example.ejercicio.block6pathvariableheaders.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PutParams {
    @PutMapping("post")

    public HashMap<Long, Long> putGreeting(@RequestParam("var1") Long var1, @RequestParam("var2") Long var2) {
        return new HashMap<Long, Long>() {{
            put(var1, var2);
        }};
    }
}
