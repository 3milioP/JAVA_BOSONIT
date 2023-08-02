package com.ejercicio.block17Batch.block17Batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing // Habilita el procesamiento por lotes
public class Block17BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block17BatchApplication.class, args);
	}

}
