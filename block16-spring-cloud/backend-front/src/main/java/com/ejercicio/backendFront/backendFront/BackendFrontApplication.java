package com.ejercicio.backendFront.backendFront;

import com.ejercicio.backendFront.backendFront.Ticket.Repository.TicketRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = TicketRepository.class)
@EnableEurekaServer
@EnableFeignClients
public class BackendFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendFrontApplication.class, args);
	}

}
