package com.ejercicio.block12kafka.Receptor.Domain;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class Receptor {

    @KafkaListener(topics = "miTema")
    @SendTo  // Esto enviará la respuesta al tema de respuesta
    public String listenAndRespond(String message) {
        // Aquí puedes procesar el mensaje recibido y generar una respuesta
        String response = "Respuesta: " + message;

        System.out.println("Mensaje recibido: " + message);
        System.out.println("Enviando respuesta: " + response);

        return response;
    }
}
