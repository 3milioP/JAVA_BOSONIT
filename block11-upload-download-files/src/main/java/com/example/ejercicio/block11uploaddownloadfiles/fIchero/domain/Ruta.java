package com.example.ejercicio.block11uploaddownloadfiles.fIchero.domain;

import jakarta.persistence.Entity;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.List;

@Component
public class Ruta implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        List<String> ruta = args.getNonOptionArgs();

        if (!ruta.isEmpty()) {
            this.ruta = ruta.get(0);
            System.out.println("Ruta: " + this.ruta);
        } else {
            this.ruta = Paths.get("").toAbsolutePath().toString();
            System.out.println("No se ha recibido ningún parámetro, la Ruta es: " + this.ruta);
        }
    }
    private String ruta;

    public Ruta() {
    }

    public Ruta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String directorio) {
        this.ruta = directorio;
    }
}
