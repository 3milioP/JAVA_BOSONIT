package com.ejercicio.block12kafka.Emisor.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Emisor {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "miTema"; // El tema al que enviaremos mensajes

    @Autowired
    public Emisor(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(topic, message);
    }
}

