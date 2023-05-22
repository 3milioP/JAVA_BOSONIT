package com.bosonit.ejercicio.block5profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class ProfilesApplication implements CommandLineRunner {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Value("${bd.url}")
	private String bdUrl;

	public static void main(String[] args) {
		SpringApplication.run(ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Active Profile: " + activeProfile);
		System.out.println("Database URL: " + bdUrl);
	}
}



