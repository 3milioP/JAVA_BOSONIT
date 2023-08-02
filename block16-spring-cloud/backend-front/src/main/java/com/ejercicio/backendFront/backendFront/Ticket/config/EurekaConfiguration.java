package com.ejercicio.backendFront.backendFront.Ticket.config;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaConfiguration {

    @Bean
    public AbstractDiscoveryClientOptionalArgs<?> discoveryClientOptionalArgs() {
        // Puedes crear una instancia de la clase AbstractDiscoveryClientOptionalArgs apropiada si es necesario,
        // o retornar null si no es requerido para tu configuración.
        return null;
    }
}
