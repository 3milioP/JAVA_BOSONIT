package com.bosonit.ejercicio.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloWorldController {
    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/")
    public String helloWorld() {
        logger.trace("Log a nivel TRACE");
        logger.debug("Log a nivel DEBUG");
        logger.info("Log a nivel INFO");
        logger.warn("Log a nivel WARN");
        logger.error("Log a nivel ERROR");


        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }

}
