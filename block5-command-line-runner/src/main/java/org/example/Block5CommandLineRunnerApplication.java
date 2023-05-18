package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class Block5CommandLineRunnerApplication implements CommandLineRunner {

	@PostConstruct
	public void init() {
		System.out.println("Hola desde clase inicial");
	}

	@Override
	public void run(String... args) {
		System.out.println("Hola desde clase secundaria");
	}
	@Component
	public class OtraClase implements CommandLineRunner {
		@Override
		public void run(String... args) {
			System.out.println("Soy la tercera clase");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
	}

}
