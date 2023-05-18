package com.bosonit.ejercicio.Maven.block5properties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MavenBlock5PropertiesApplication {

	@Value("${greeting}")
	private String greeting;
	@Value("${my.number}")
	private int number;
	@Value("${new.property:new.property no tiene valor}")
	String newProperty;

	@Value("${variable1:no tiene valor}")
	String variable;

	@PostConstruct
	public void	pintaSaludo() {
		System.out.println(greeting);
		System.out.println(number);
		System.out.println(newProperty);
		System.out.println(variable);
	}


	public static void main(String[] args) {
		SpringApplication.run(MavenBlock5PropertiesApplication.class, args);

	}
}
