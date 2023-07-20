package com.ejercicio.block12kafka.Emisor.Controller;

import com.ejercicio.block12kafka.Emisor.Domain.Emisor;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmisorController {

    private final Emisor emisor;

    @Autowired
    public EmisorController(Emisor emisor) {
        this.emisor = emisor;
    }

    @PostMapping("/enviarMensaje")
    public void enviarMensaje(@RequestBody String mensaje) {
        emisor.sendMessage(mensaje);
    }
}
