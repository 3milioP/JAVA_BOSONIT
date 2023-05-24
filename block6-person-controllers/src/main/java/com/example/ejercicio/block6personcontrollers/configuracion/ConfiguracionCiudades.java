package com.example.ejercicio.block6personcontrollers.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.ejercicio.block6personcontrollers.modelos.Ciudad;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfiguracionCiudades {
    @Bean
    public List<Ciudad> listaCiudades() {
        return new ArrayList<>();
    }
}
